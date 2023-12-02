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
            myRobot.turnRight();
            sleep(450);
            myRobot.driveStop();
//            myRobot.driveBack();
//            sleep(3000);
//            myRobot.driveStop();
//            myRobot.topPosition();
//            myRobot.dropPosition();
//            myRobot.openClaw();
            canRun = false; // make sure loop doesn't run again
        }
    }
}
