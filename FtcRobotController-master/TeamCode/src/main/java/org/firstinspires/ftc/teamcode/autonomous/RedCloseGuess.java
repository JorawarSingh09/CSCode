package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Red: close - Place pixel close and park", group = "Linear OpMode")
public class RedCloseGuess extends LinearOpMode implements AutonomousBase{
    CenterStageRobot myRobot;
    public static int driveToProp = 1200, turnDistance = 300;

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
            canRun = false; // make sure loop doesnt run again//

        }
    }

    @Override
    public void defaultDropAndPark() {

    }

    @Override
    public void dropPixelCenter() {

    }

    @Override
    public void dropPixelLeft() {

    }

    @Override
    public void dropPixelRight() {

    }

    @Override
    public void park() {

    }

    @Override
    public void shakePixel() {

    }
}
