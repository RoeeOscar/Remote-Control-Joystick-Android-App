package com.example.android_app_remote_control_joystick.viewModel

import com.example.android_app_remote_control_joystick.model.MyModel
import java.net.InetAddress

class MyViewModel{
    var model: MyModel = MyModel()

    // Connect to FlightGear Server.
    fun connect(ipAddress: InetAddress, portNumber: Int){
        model.connect(ipAddress,portNumber)
    }

    // Disconnect from FlightGear server.
    fun disconnect(){
        model.disconnect()
    }

    // Set the airplane's rudder.
    fun setRudder(value: Double){
        model.setRudder(value)
    }

    // Set the airplane's throttle.
    fun setThrottle(value: Double){
        model.setThrottle(value)
    }

    // Set the airplane's aileron.
    fun setAileron(value: Float){
        model.setAileron(value)
    }

    // Set the airplane's elevator.
    fun setElevator(value: Float){
        model.setElevator(value*-1)
    }
}