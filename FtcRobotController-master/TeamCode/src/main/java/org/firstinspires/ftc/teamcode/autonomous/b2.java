package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Basic: b2", group = "Linear OpMode")
public class b2 extends LinearOpMode {
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
            myRobot.strafeLeft();
            sleep(2000);
            myRobot.driveBack();
            sleep(3000);
            myRobot.driveStop();
            myRobot.topPosition();
            sleep(3000);
            myRobot.dropPosition();
            sleep(1000);
            myRobot.openClaw();
            canRun = false; // make sure loop doesnt run again//
            sleep(50000);
        }
    }
}
