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
            myRobot.drivePosition();
            myRobot.driveForward();
            sleep(1800);
            myRobot.driveStop();
            myRobot.dropPosition();
            myRobot.openClaw();
            myRobot.drivePosition();
            myRobot.closeClaw();
            myRobot.driveBack();
            sleep(1500);
            myRobot.driveStop();
            myRobot.turnLeft();
            sleep(450);
            myRobot.driveStop();
            myRobot.driveForward();
            sleep(4500);
            myRobot.driveStop();
            myRobot.strafeRight();
            sleep(4000);
            myRobot.driveStop();
            myRobot.driveForward();
            sleep(1500);
            myRobot.driveStop();

            canRun = false; // make sure loop doesn't run again
        }
    }
}
