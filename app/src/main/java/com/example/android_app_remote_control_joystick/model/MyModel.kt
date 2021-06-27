package com.example.android_app_remote_control_joystick.model

import java.io.PrintWriter
import java.net.InetAddress
import java.net.Socket
import java.util.concurrent.ForkJoinPool

class MyModel {
    private var fgSocket: Socket? = null
    private var printWriter:PrintWriter?=null
    private val fjp: ForkJoinPool =
        ForkJoinPool(1, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true)

    // Connect to flightGear server.
    fun connect(ipAddress: InetAddress, portNumber: Int) {
        fjp.execute{
            fgSocket =Socket(ipAddress,portNumber)
            printWriter=PrintWriter(fgSocket?.outputStream, true)
        }
    }

    // Disconnect from flightGear server.
    fun disconnect() {
        fgSocket?.close()
    }

    // Set the airplane's rudder.
    fun setRudder(value: Double){
        fjp.execute{
            val s = "set /controls/flight/rudder $value \r\n"
            printWriter?.write(s)
            printWriter?.flush()
        }
    }

    // Set the airplane's throttle.
    fun setThrottle(value: Double){
        fjp.execute{
            val s = "set /controls/engines/current-engine/throttle $value \r\n"
            printWriter?.write(s)
            printWriter?.flush()
        }
    }

    // Set the airplane's aileron.
    fun setAileron(value: Float){
        fjp.execute{
            val s = "set /controls/flight/aileron $value \r\n"
            printWriter?.write(s)
            printWriter?.flush()
        }
    }

    // Set the airplane's elevator.
    fun setElevator(value: Float){
        fjp.execute{
            val s = "set /controls/flight/elevator $value \r\n"
            printWriter?.write(s)
            printWriter?.flush()
        }
    }
}