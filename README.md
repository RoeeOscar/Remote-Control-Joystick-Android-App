# Remote Control Joystick - Android Application

## Table of contents
1. [About the application](#about)  


<a name="about"></a>
## About the application
The application is written in Kotlin. It uses MVVM (Model-View-ViewModel) architectural pattern, and also uses strategy design pattern and Fork-Join-Pool service.

### The View
The view includes two parts:
* The top part is used for connecting and disconnecting to the FlightGear server. The user is required for entering the IP Address and the Port 	Number of the FlightGear server, and then clicking the 	“Connect to FlightGear” button for connecting to the server. After the connecting to the server, the disconnecting button will 	be enabled, so the user can disconnect from the server.
* The bottom part is used for controlling the airplane: the throttle is controlled by the left seek bar, the rudder is controlled by the bottom seek bar, and the aileron and the elevator are controlled by the joystick.

The view passes its data to the viewModel.

![image](https://user-images.githubusercontent.com/81086558/123554045-f15e2300-d786-11eb-8e37-b93c26d178d3.png)

### The ViewModel
The ViewModel is used for passing data from the View to the Model by request.

### The Model
The Model is used for communicating with the FlightGear server. First of all, it connects the FlightGear server, and then, after getting data from the ViewModel by request from the view, it sends the FlightGear server the compatible string in order to set the throttle, the rudder, the elevator and the aileron.
At the end, the Model disconnects from the Server.

