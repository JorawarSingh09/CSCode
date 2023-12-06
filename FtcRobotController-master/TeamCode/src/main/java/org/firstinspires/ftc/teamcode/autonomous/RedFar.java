package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Red: far - Place pixel in middle and park", group = "Linear OpMode")
public class RedFar extends LinearOpMode {
    CenterStageRobot myRobot;
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
            myRobot.driveStop();
            myRobot.pickupPosition();
            sleep(500);
            myRobot.driveBack();
            sleep(200);
            myRobot.driveStop();
            myRobot.closeClaw();
            myRobot.drivePosition();
            myRobot.driveBack();
            sleep(1100);
            myRobot.driveStop();
            myRobot.strafeRight();
            sleep(8000);
            myRobot.driveForward();
            sleep(2000);
            myRobot.strafeRight();
            sleep(2000);
            myRobot.driveStop();
        }
    }
}
