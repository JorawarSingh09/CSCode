package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Blue: Far - place pixel in middle and park", group = "Linear OpMode")
public class BlueFarGuess extends LinearOpMode implements AutonomousBase{
    CenterStageRobot myRobot;
    public static int driveToScanArea = 1500, turnDistance = 300;
    @Override
    public void runOpMode() throws InterruptedException {
        myRobot = new CenterStageRobot(hardwareMap, telemetry);

        myRobot.startPosition();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        boolean canRun = true;

        while(opModeIsActive() && canRun){
            defaultDropAndPark();
            canRun = false; // make sure loop doesn't run again
        }
    }

    @Override
    public void defaultDropAndPark() {
        myRobot.driveForward();
        sleep(driveToScanArea);
        dropPixelCenter();
        park();
        shakePixel();

    }

    @Override
    public void dropPixelCenter() {
        myRobot.driveForward();
        sleep(1700 - driveToScanArea);
        myRobot.driveStop();
        myRobot.strafeRight();
        sleep(400);
        myRobot.pickupPosition();
        myRobot.driveBack();
        sleep(500);
        myRobot.strafeLeft();
        sleep(400);
        myRobot.driveStop();
    }

    @Override
    public void dropPixelLeft() {
        // TODO
    }

    @Override
    public void dropPixelRight() {
        // TODO
    }

    @Override
    public void park() {
        myRobot.drivePosition();
        myRobot.strafeLeft();
        sleep(4000);
        myRobot.driveStop();
        myRobot.driveForward();
        sleep(1900);
        myRobot.strafeLeft();
        sleep(1500);
        myRobot.driveStop();
    }

    @Override
    public void shakePixel() {
        myRobot.turnLeft();
        sleep(200);
        myRobot.turnRight();
        sleep(200);
        myRobot.turnLeft();
        sleep(500);
        myRobot.turnRight();
        sleep(200);
        myRobot.driveStop();
    }
}
