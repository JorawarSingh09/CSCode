package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Basic: b1", group = "Linear OpMode")
public class b1 extends LinearOpMode {
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
            myRobot.strafeLeft();
            sleep(8000);
            myRobot.driveForward();
            sleep(2000);
            myRobot.strafeLeft();
            sleep(2000);
            myRobot.driveStop();

            canRun = false; // make sure loop doesn't run again
        }
    }
}
