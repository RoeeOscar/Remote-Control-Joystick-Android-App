package com.example.android_app_remote_control_joystick.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.android_app_remote_control_joystick.R
import com.example.android_app_remote_control_joystick.viewModel.MyViewModel
import java.lang.NumberFormatException
import java.net.InetAddress

class MainActivity : AppCompatActivity() {

    private var ipAddress: String = ""
    private var portNumber: Int = 0
    private var viewModel: MyViewModel? = null

    @SuppressLint("SetTextI18n", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        // Creating the view.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = MyViewModel()

        val connectButton = findViewById<Button>(R.id.connect_button)
        val disconnectButton = findViewById<Button>(R.id.disconnect_button)

        // Connect button listener.
        connectButton.setOnClickListener {
            try {
                ipAddress = findViewById<EditText>(R.id.IP_Address).text.toString()
                portNumber = findViewById<EditText>(R.id.Port_Number).text.toString().toInt()
                val inetAddress:InetAddress = InetAddress.getByName(ipAddress)
                connectButton.text = "Connected"
                disconnectButton.text="Disconnect from FlightGear"
                connectButton.isEnabled=false
                findViewById<EditText>(R.id.IP_Address).isEnabled=false
                findViewById<EditText>(R.id.Port_Number).isEnabled=false
                findViewById<Button>(R.id.disconnect_button).isEnabled=true
                viewModel!!.connect(inetAddress, portNumber)

            } catch (ex: Exception){
                when(ex){
                    is NumberFormatException, is NetworkOnMainThreadException ->{
                        // Show alert message for wrong IP address or port number.
                        val alertBuilder = AlertDialog.Builder(this)
                        alertBuilder.setTitle("Wrong IP Address or Port Number")
                        alertBuilder.setMessage("Please enter correct IP Address and Port Number.")

                        alertBuilder.setPositiveButton(android.R.string.ok) { dialog, which ->
                            Toast.makeText(applicationContext,
                                android.R.string.ok, Toast.LENGTH_SHORT).show()
                        }
                        alertBuilder.show()
                    }
                }

            }
        }

        // Disconnect button listener.
        disconnectButton.setOnClickListener {
            connectButton.text = "Connect to FlightGear"
            connectButton.isEnabled=true
            disconnectButton.text="Disconnected"
            findViewById<EditText>(R.id.IP_Address).isEnabled=true
            findViewById<EditText>(R.id.Port_Number).isEnabled=true
            findViewById<Button>(R.id.disconnect_button).isEnabled=false
            viewModel!!.disconnect()
        }

        val rudderSeekBar = findViewById<SeekBar>(R.id.rudder_bar)

        // Rudder seek bar listener.
        rudderSeekBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                val value: Double = (progress / 100000.0) - 1
                viewModel!!.setRudder(value)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                return
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                return
            }
        })

        // Throttle seek bar listener.
        val throttleSeekBar = findViewById<SeekBar>(R.id.throttle_bar)
        throttleSeekBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                val value: Double = (progress / 100000.0)
                viewModel!!.setThrottle(value)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                return
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                return
            }
        })

        val joystick = findViewById<Joystick>(R.id.joystick)

        // Joystick listener.
        joystick.onChange = object : OnJoystickChange {
            override fun onChange(aileron: Float, elevator: Float) {
                viewModel!!.setAileron((aileron-455)/455.0f)
                viewModel!!.setElevator(((elevator-455)/455.0f))
            }
        }
    }
}