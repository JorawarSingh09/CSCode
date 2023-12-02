package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Basic: r2", group = "Linear OpMode")
public class r2 extends LinearOpMode {
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
            myRobot.strafeRight();
            sleep(2000);
            myRobot.driveBack();
            sleep(3000);
            myRobot.driveStop();
            myRobot.topPosition();
            sleep(3000);
            myRobot.dropPosition();
            sleep(1000);
            myRobot.openClaw();
            myRobot.driveForward();
            sleep(500);
            myRobot.startPosition();
            canRun = false; // make sure loop doesnt run again//

        }
    }
}
