package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Disabled
@Autonomous(name = "USED FOR TESTING ONLY", group = "Linear OpMode")
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
            sleep(1300);
            myRobot.turnLeft();
            sleep(800);
            myRobot.driveForward();
            sleep(150);
            myRobot.driveStop();
            myRobot.pickupPosition();
            myRobot.driveBack();
            sleep(450);
            myRobot.driveStop();
            myRobot.closeClaw();
            myRobot.drivePosition();



            canRun = false; // make sure loop doesn't run again
        }
    }
}
