package org.firstinspires.ftc.teamcode.autonomous;

//import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.GrabberArm;
import org.firstinspires.ftc.teamcode.subsystems.GrabberState;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.subsystems.MechanismState;

/*
 * This file contains an example of a Linear "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When a selection is made from the menu, the corresponding OpMode is executed.
 *
 * This particular OpMode illustrates driving a 4-motor Omni-Directional (or Holonomic) robot.
 * This code will work with either a Mecanum-Drive or an X-Drive train.
 * Both of these drives are illustrated at https://gm0.org/en/latest/docs/robot-design/drivetrains/holonomic.html
 * Note that a Mecanum drive must display an X roller-pattern when viewed from above.
 *
 * Also note that it is critical to set the correct rotation direction for each motor.  See details below.
 *
 * Holonomic drives provide the ability for the robot to move in three axes (directions) simultaneously.
 * Each motion axis is controlled by one Joystick axis.
 *
 * 1) Axial:    Driving forward and backward               Left-joystick Forward/Backward
 * 2) Lateral:  Strafing right and left                     Left-joystick Right and Left
 * 3) Yaw:      Rotating Clockwise and counter clockwise    Right-joystick Right and Left
 *
 * This code is written assuming that the right-side motors need to be reversed for the robot to drive forward.
 * When you first test your robot, if it moves backward when you push the left stick forward, then you must flip
 * the direction of all 4 motors (see code below).
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

//@Config
@Disabled
@Autonomous(name = "Basic: Jauto", group = "Linear OpMode")
public class Jauto extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private LinearSlides linearSlides = null;
    private GrabberArm grabberArm = null;
    private CRServo planeLauncher = null;
    private MechanismState mechanismState = MechanismState.INIT;

    private void setDrive(){
        // Initialize the hardware variables. Note that the strings used here must
        // correspond
        // to the names assigned during the robot configuration step on the DS or RC
        // devices.
        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");

        planeLauncher = hardwareMap.get(CRServo.class, "plane_launcher");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
    }
    private double[] drive(double axial, double lateral, double yaw){
        double max;
//        drivePosition();
        double leftFrontPower = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower = axial - lateral + yaw;
        double rightBackPower = axial + lateral - yaw;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftBackDrive.setPower(leftBackPower);
        rightBackDrive.setPower(rightBackPower);
        double[] drivePower = {leftFrontPower, rightFrontPower, leftBackPower, rightBackPower};
        return drivePower;
    }

    private void stopDrive(){
        drive(0,0,0);
    }
    private void telemetry(){
        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
//            telemetry.addData("Front left/Right", "%4.2f, %4.2f", drivePower[0], drivePower[1]);
//            telemetry.addData("Back left/Right", "%4.2f, %4.2f", drivePower[2], drivePower[3]);
//            telemetry.addData("Trigger Values", "%d, %d", gamepad1.right_trigger, gamepad1.left_trigger);
        telemetry.addData("Linear Slide Position left/right: ", "%d, %d",
                linearSlides.getPosition()[0], linearSlides.getPosition()[1]);
        telemetry.addData("Arm Position: ", "%d", grabberArm.getArm());
        telemetry.addData("Wrist Position: ", "%4.2f", grabberArm.getWrist());
        telemetry.addData("Claw Position: ", "%4.2f", grabberArm.getClaw());


//        pk.put("Status", "Run Time: " + runtime.toString());


//        dashboard.sendTelemetryPacket(pk);
        telemetry.update();
    }
    private void startPosition(){
        mechanismState = MechanismState.INIT;
        //set Arm position to bottom
        grabberArm.setArmPosition(0);
        sleep(100);
        // set Wrist to fold up
        grabberArm.setWristPosition(1);
        sleep(100);
        //set Claw to open
        grabberArm.closeClaw();
        sleep(100);
        // linear slides pos
        linearSlides.bottomPosition();
    }
    private void drivePosition(){
        stopDrive();
        if(mechanismState == MechanismState.PICKUP && grabberArm.getGrabberState() == GrabberState.CLOSED){
            mechanismState = MechanismState.DRIVE;
            grabberArm.setArmPosition(100);
        }
    }
    private void pickupPosition(){
        stopDrive();
        if(mechanismState == MechanismState.PICKUP) return;
        mechanismState = MechanismState.PICKUP;

        grabberArm.setArmPosition(200);
        sleep(100);
        linearSlides.bottomPosition();
        sleep(200);
        grabberArm.setArmPosition(20);
        sleep(100);
        grabberArm.setWristPosition(0);
        sleep(100);
        grabberArm.openClaw();
    }
    private void topPosition(){
        stopDrive();
        if(mechanismState == MechanismState.TOP_POSITION) return;
        mechanismState = MechanismState.TOP_POSITION;

        grabberArm.setWristPosition(0);
        sleep(100);
        grabberArm.closeClaw();
        sleep(100);
        grabberArm.setArmPosition(520);
        sleep(100);
        linearSlides.topPosition();
    }
    private void dropPosition(){
        stopDrive();
        if(mechanismState == MechanismState.DROP_POSITION) return;
        mechanismState = MechanismState.DROP_POSITION;
        grabberArm.setWristPosition(0.5);
        sleep(100);
        grabberArm.closeClaw();
        sleep(100);
        grabberArm.setArmPosition(835);
        sleep(200);
        linearSlides.topPosition();
    }

    // Strafe Left

    // Strafe Right

    // Forwards

    // Backwards

    //Turn Left
    @Override
    public void runOpMode() {

        // Initialize the hardware variables. Note that the strings used here must
        // correspond
        // to the names assigned during the robot configuration step on the DS or RC
        // devices.
        setDrive();
        linearSlides = new LinearSlides(hardwareMap);
        grabberArm = new GrabberArm(hardwareMap);
        telemetry.addData("Status", "Initialized");
//        pk.put("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();
        boolean canRun = true;
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if(canRun) {
                //Put autonomous code here homies
            }
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Trigger Values", "%4.2f, %4.2f", gamepad1.right_trigger, gamepad1.left_trigger);
            telemetry.update();
        }
    }


}
