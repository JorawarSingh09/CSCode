package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.CenterStageRobot;

@Autonomous(name = "Basic: r1", group = "Linear OpMode")
public class r1 extends LinearOpMode {
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
//            myRobot.pickupPosition();
//            sleep(2000);
//            myRobot.closeClaw();
//            sleep(2000);
//            myRobot.topPosition()

            // example code Follow me
//            myRobot.driveForward();
//            sleep(1000);
//            myRobot.driveStop();

            
            //start code here
            sleep(50000);
        }
    }
}
