package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Red: far - Place pixel in middle and park", group = "Linear OpMode")
public class RedFarGuess extends LinearOpMode {
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
            sleep(1700);
            myRobot.driveStop();
            myRobot.pickupPosition();
            myRobot.driveBack();
            sleep(500);
            myRobot.drivePosition();
            myRobot.strafeRight();
            sleep(4000);
            myRobot.driveStop();
            myRobot.driveForward();
            sleep(1900);
            myRobot.strafeRight();
            sleep(1500);
            myRobot.driveStop();
            myRobot.turnRight();
            sleep(200);
            myRobot.turnLeft();
            sleep(200);
            myRobot.turnRight();
            sleep(500);
            myRobot.turnLeft();
            sleep(200);
            myRobot.driveStop();
        }
    }
}
