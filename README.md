# FTC 2023 - Centerstage

## Diary
### Week 1 Term 4
Kiana and Bhargav this is what we need to do this week! - Jora

#### GIT
I've added a working base for out teamCode, but first we need to pull all the changes I made to your local PC(s). Today you can either choose to work together or seperately.

Lets start.
First lets open up terminal/powershell and navigate to our FTC folder. If your folder is in Documents follow the example below.

```

> cd Documents/FTC

```
 If your having trouble finding your folder through the terminal. Delete the copy of FTC you currently have and enter these commands into terminal/powershell. NOTE: the '>' is not part of the command but indicates that its a new line in the terminal.

 ```
 > cd ~
 > cd Documents
 > git clone git@github.com:JorawarSingh09/FTC.git
 ```

 This wil download the latest version of the repo to your computer. From now on if you need to download the repo  again you will need to "cd" into the "FTC" folder and use the command "git pull".
 ```
 > cd ~
 > cd Documents\FTC
 > git pull
 ```

 ### Code
 Open up the folder FtcRobotControllerMaster Controller in Android studio. 

 Open up the file  "JOmni.java" which can be found in TeamCode\src\java.
 This is the current Teleop Code that we're working with. We talked about Java in the holiday session so I'm going to give a short explanation of what everythign does.

 ```
 @TeleOp(name="Basic: Jomni", group="Linear OpMode")
 ```
 This is what shows up in our driver station and set the name and mode of the code.

 ```
 public class JOmni extends LinearOpMode {}
 ```
 This creates the JOmni class, which is a child of the LinearOpMode class and thus inherits all of LinearOpModes features, such as objects contained within, quickly look the LinearOpMode class and discern the differences.

 ```
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor linearSlide = null;
 ```
Here we are creating a bunch of different objects, mainly for our motors. We are also creating an object for Elapsed time. we can use this for operations that require time based actions. Time is the only object that isnt empty, since it is initialised using the new ElapsedTime constructor. 

```
public void runOpMode()
```
This is the method that gets called by the driver station when we hit play. Everything in this method (or function is another name) is run on start.

```
leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        linearSlide = hardwareMap.get(DcMotor.class, "linear_slide");
```
This section gets the motor mapping, or where we have plugged in our motors and other hardware in the robot. This information is taken from the hardwareMap, which is the configuration info we set in the driverstation.

### Task 1: Comment out OMniCode
To get a goood understanding of the code I want you comment as much of the code you can and use your own words to describe that the function of the code is. For all Objects in the code I want you to write out what the object type is and how its initialised if at all.

Once this is done send the code to the Robot. COnnect to the control hub using usb c and you should see the control hub pop up in android studio in the top bar next to the play sign. Press the play and wait for the code to be pushed.

### Task 2: Code for linear slides
You may Have noticed a TODO notice in your Code. I need you guys to work together and make the code for the linear slides. Look to the code that makes the drivetrain motors to work for inspiration.
The Requirements will be:
- Left trigger on the controller will make the slides do DOWN
- Right trigger will make the slides go up
- MAKE SURE THIS IS SLOW, we dont have any hard limits yet so chill.

What you basically need to do is SET the POWER of the linearSlides motor to the value of the triggers. You may need to scale this value in order to restrict power, you can chcek what values the triggers output in the Telemetry menu in the DriverStation (Bhargav Phone). Just run it and play around with the controller to see what the min and max values are. 


### Task 3: What do we need?
#### Bhargav
Talk to the mech team about linear slides. How do they need it to function? do they need it to stop at certain levels. Move at a certain speed. Ask them everything you can and write the QA below (ReadMe File)
Q:
A:

Q:
A:

#### Kianna
Talk to the mech team about the grabbing mech. How do they need it to function? How should the servo work> DO they want set movement or adjustable movement. Ask them everything you can and write the QA below (ReadMe File)

Q:
A:

Q:
A:
