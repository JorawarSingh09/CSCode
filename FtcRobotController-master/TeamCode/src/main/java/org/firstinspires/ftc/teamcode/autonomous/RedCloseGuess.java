package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Red: close - Place pixel close and park", group = "Linear OpMode")
public class RedCloseGuess extends LinearOpMode implements AutonomousBase{
    CenterStageRobot myRobot;
    public static int driveToScanArea = 1300, turnDistance = 300;

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
            sleep(driveToScanArea);
            myRobot.driveStop();
            defaultDropAndPark();
            canRun = false; // make sure loop doesnt run again//

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
        sleep(1800 - driveToScanArea);
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

    }

    @Override
    public void dropPixelRight() {

    }

    @Override
    public void park() {
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
