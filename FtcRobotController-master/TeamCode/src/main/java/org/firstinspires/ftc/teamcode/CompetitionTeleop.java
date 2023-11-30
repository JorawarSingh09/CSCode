package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name = "Basic: CompTeleOp", group = "Linear OpMode")
public class CompetitionTeleop extends LinearOpMode {

    RobotInterface myRobot;
    Gamepad driver;
    Gamepad operator;
    @Override
    public void runOpMode() throws InterruptedException {
        myRobot = new RobotInterface(hardwareMap, telemetry);

        myRobot.startPosition();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            driver = gamepad1;
            operator = gamepad2;
            // Driver Controls ===================
            // Drive
            double axial = -gamepad1.left_stick_y;
            double lateral = gamepad1.left_stick_x;
            double yaw = gamepad1.right_stick_x;
            myRobot.drive(axial, lateral, yaw);

            // raise claws for climb
            if(driver.dpad_up){
                myRobot.raiseHooks();
            }
            // Climb
            if(driver.dpad_down){
                myRobot.climb();
            }
            // launch plane and reset the servo
            if(driver.a){
                myRobot.launchPlane();
                sleep(1000);
                myRobot.resetLauncher();
            }
            // Operator Controls =================
            // open claw
            if(operator.right_trigger > 0){
                myRobot.openClaw();
            }

            // close claw
            if(operator.left_trigger > 0){
                myRobot.closeClaw();
            }

            // Adjusting linear slides up
            if(operator.right_bumper){
                myRobot.adjustSlides(10);
            }

            if(operator.right_bumper){
                myRobot.adjustSlides(-10);
            }

            // Pickup Position
            if(operator.a){
                myRobot.pickupPosition();
            }

            // drive position
            if(operator.x){
                myRobot.drivePosition();
            }
            // Top position
            if(operator.y){
                myRobot.topPosition();
            }

            // Position where we drop the pixel
            if (operator.b) {
                myRobot.dropPosition();
            }
        }
    }
}
