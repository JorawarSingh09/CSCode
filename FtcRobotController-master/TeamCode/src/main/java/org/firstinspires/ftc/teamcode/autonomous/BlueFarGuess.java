package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Blue: Far - place pixel in middle and park", group = "Linear OpMode")
public class BlueFarGuess extends LinearOpMode {
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
            myRobot.strafeLeft();
            sleep(4000);
            myRobot.driveStop();
            myRobot.driveForward();
            sleep(1900);
            myRobot.strafeLeft();
            sleep(1500);
            myRobot.driveStop();
            myRobot.turnLeft();
            sleep(200);
            myRobot.turnRight();
            sleep(200);
            myRobot.turnLeft();
            sleep(500);
            myRobot.turnRight();
            sleep(200);
            myRobot.driveStop();

            canRun = false; // make sure loop doesn't run again
        }
    }
}
