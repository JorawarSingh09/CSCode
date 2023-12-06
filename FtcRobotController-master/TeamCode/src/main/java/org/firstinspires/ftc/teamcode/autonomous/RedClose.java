package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Red: close - Place pixel close and park", group = "Linear OpMode")
public class RedClose extends LinearOpMode {
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
            sleep(1000);
            myRobot.driveStop();
            myRobot.openClaw();
            myRobot.driveBack();
            sleep(500);
            myRobot.drivePosition();
            myRobot.strafeLeft();
            sleep(1000);
            myRobot.driveForward();
            sleep(1000);
            myRobot.strafeLeft();
            sleep(1500);
            canRun = false; // make sure loop doesnt run again//

        }
    }
}
