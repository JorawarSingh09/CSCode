package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.CenterStageRobot;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name = "VisionTest", group = "Linear OpMode")
public class VisionTest extends LinearOpMode {
    FtcDashboard dashboard = FtcDashboard.getInstance();
    CenterStageRobot myRobot;
    Gamepad driver;
    Gamepad operator;

    WebcamName camera;
    VisionPortal visionPortal;
    @Override
    public void runOpMode() throws InterruptedException {
        myRobot = new CenterStageRobot(hardwareMap, telemetry);
        AprilTagProcessor april = AprilTagProcessor.easyCreateWithDefaults();
        camera = hardwareMap.get(WebcamName.class, "Webcam");
        visionPortal = VisionPortal.easyCreateWithDefaults(camera, april);

        myRobot.startPosition();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {

        }
        myRobot.reset();
    }
}
