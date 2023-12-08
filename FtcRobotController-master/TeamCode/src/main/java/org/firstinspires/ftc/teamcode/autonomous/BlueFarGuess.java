package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Blue: Far - NEEDS TO BE TESTED", group = "Linear OpMode")
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
        dropPixelCenter();
        park();
        shakePixel();

    }

    @Override
    public void dropPixelCenter() {
        myRobot.pushPosition();
        myRobot.driveForward();
        sleep(1715);
        myRobot.driveStop();
        myRobot.pickupPosition();
        myRobot.driveStop();
        sleep(100);
        myRobot.driveBack();
        sleep(200);
        myRobot.driveStop();
        myRobot.closeClaw();
        myRobot.drivePosition();
    }

    @Override
    public void dropPixelLeft() {
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
    }

    @Override
    public void dropPixelRight() {
        myRobot.driveForward();
        sleep(1300);
        myRobot.turnRight();
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
    }

    @Override
    public void park() {
        myRobot.driveBack();
        sleep(1100);
        myRobot.strafeLeft();
        sleep(8000);
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
        myRobot.turnLeft();
        sleep(200);
        myRobot.turnRight();
        sleep(500);
        myRobot.driveStop();
    }
}
