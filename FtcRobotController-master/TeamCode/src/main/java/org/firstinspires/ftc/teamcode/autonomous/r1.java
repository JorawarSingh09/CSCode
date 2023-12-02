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
<<<<<<< HEAD
            
=======
//            myRobot.pickupPosition();
//            sleep(2000);
//            myRobot.closeClaw();
//            sleep(2000);
//            myRobot.topPosition()

            // example code Follow me
//            myRobot.driveForward();
//            sleep(1000);
//            myRobot.driveStop();


>>>>>>> bdd3052fa095f790d82612801a3a9cc73fee84e2
            //start code here

        }
    }
}
