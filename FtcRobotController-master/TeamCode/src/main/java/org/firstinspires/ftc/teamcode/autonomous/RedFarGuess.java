package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Red: far - Place pixel in middle and park", group = "Linear OpMode")
public class RedFarGuess extends LinearOpMode implements AutonomousBase {
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
        sleep(1800);
        myRobot.driveStop();
        myRobot.strafeRight();
        sleep(400);
        myRobot.pickupPosition();
        myRobot.driveBack();
        sleep(500);
        myRobot.closeClaw();
        myRobot.drivePosition();
        myRobot.strafeLeft();
        sleep(400);
        myRobot.driveStop();
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
        myRobot.drivePosition();
        myRobot.strafeRight();
        sleep(4000);
        myRobot.driveStop();
        myRobot.driveForward();
        sleep(1900);
        myRobot.strafeRight();
        sleep(1500);
        myRobot.driveStop();
    }

    @Override
    public void shakePixel() {
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
