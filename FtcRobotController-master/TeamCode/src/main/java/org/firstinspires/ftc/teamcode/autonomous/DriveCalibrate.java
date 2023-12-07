package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Use to calibrate Strafe", group = "Linear OpMode")
public class DriveCalibrate extends LinearOpMode {
    CenterStageRobot myRobot;
    Gamepad driver;
    Gamepad operator;

    @Override
    public void runOpMode() throws InterruptedException {
        myRobot = new CenterStageRobot(hardwareMap, telemetry);

        myRobot.startPosition();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        boolean canRun = true;

        while(opModeIsActive() && canRun){
            myRobot.driveForward();
            sleep(1800);
            myRobot.turnLeft();
            sleep(200);
            myRobot.driveForward();
            sleep(200);
            myRobot.driveStop();
            myRobot.pickupPosition();
//            myRobot.driveBack();
//            sleep(200);
//            myRobot.turnRight();
//            sleep(200);
//            myRobot.driveBack();
//            sleep(1800);


            canRun = false; // make sure loop doesn't run again
        }
    }
}
