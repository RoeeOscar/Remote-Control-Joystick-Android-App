# Remote Control Joystick - Android Application

## Table of contents
1. [About the application](#about)  
1.1. [View](#view)
1.2. [ViewModel](#viewmodel)
1.3. [Model](#model)
2. [Prerequisites](#prerequisites)
3. [Running the Application](#running)
4. [UML Diagram](#uml)
5. [Video & Presenation](#video)
<a name="about"></a>
## About the application
The application is written in Kotlin. It uses MVVM (Model-View-ViewModel) architectural pattern, and also uses strategy design pattern and Fork-Join-Pool service.

<a name="view"></a>
### The View
The view includes two parts:
* The top part is used for connecting and disconnecting to the FlightGear server. The user is required for entering the IP Address and the Port 	Number of the FlightGear server, and then clicking the 	“Connect to FlightGear” button for connecting to the server. After the connecting to the server, the disconnecting button will 	be enabled, so the user can disconnect from the server.
* The bottom part is used for controlling the airplane: the throttle is controlled by the left seek bar, the rudder is controlled by the bottom seek bar, and the aileron and the elevator are controlled by the joystick.

The view passes its data to the viewModel.

![image](https://user-images.githubusercontent.com/81086558/123554045-f15e2300-d786-11eb-8e37-b93c26d178d3.png)

This is the state of the application after connecting to the FlightGear server:

![image](https://user-images.githubusercontent.com/81086558/123558217-ba473c00-d79d-11eb-9fc3-a1bfabb8b25f.png)

This is the alert that is shown after entering wrong IP Address or Port Number:

![image](https://user-images.githubusercontent.com/81086558/123558222-c3380d80-d79d-11eb-8288-42388ec97cef.png)

<a name="viewmodel"></a>
### The ViewModel
The ViewModel is used for passing data from the View to the Model by request.

<a name="model"></a>
### The Model
The Model is used for communicating with the FlightGear server. First of all, it connects the FlightGear server, and then, after getting data from the ViewModel by request from the view, it sends the FlightGear server the compatible string in order to set the throttle, the rudder, the elevator and the aileron.


At the end, the Model disconnects from the Server.


<a name="prerequisites"></a>
## Prerequisites
1. Download **[Android Studio](https://developer.android.com/studio)** or **[IntelliJ](https://www.jetbrains.com/idea/download/)** and install it.
2. **[Download](https://www.flightgear.org/download/)** FlightGear and install it.
3. Open the **FlightGear** application, go to **Settings** and add the following **Additional settings**:
>```--telnet=socket,in,10,127.0.0.1,6400,tcp```

You can also use another available port instead of 6400.

4. Get your **IPv4 Address**:

* Open the **Command Line**.
* Run the **'ipconfig'** command.
* Get your **IPv4 Address**.

<a name="running"></a>
## Running the Application
1. Click on **Fly!** button in the FlightGear application.
2. Open the **terminal** and run the following command:
>```git clone https://github.com/RoeeOscar/Remote-Control-Joystick-Android-App.git```
3.  Open **Android Studio** or **Intellij** and import the project from the cloning destination path.
4. Run the application.
5. Enter your **IPv4 Address** in the **IP Address** field.
6. Enter port **6400** (or the other port number you have chosen) in the **Port Number** field.
7. Click the **"Connect to FlightGear"** Button.
8. Now, you can control the airplane.
9. For disconnecting, click the **"Disconnect from FlightGear"** button.

<a name="uml"></a>
## UML Diagram
Here is the **UML Diagram** for the application:
![XML](https://user-images.githubusercontent.com/81086558/123558482-27a79c80-d79f-11eb-8b26-a28f4024af4a.png)


<a name="video"></a>
## Video & Presentation
You can watch a presentation and a demo for the application **[here](https://www.youtube.com/watch?v=Cl4oZMBwgh0)**.
