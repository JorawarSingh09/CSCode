package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.teamcode.CenterStageRobot;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@Config
@Autonomous(name = "Red: close - Implements comp vision to place pixel",
        group = "Linear OpMode")
public class RedCloseCompVision extends LinearOpMode {
    CenterStageRobot myRobot;
    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashTelemetry = dashboard.getTelemetry();
    private static final String TFOD_MODEL_ASSET = "RedProp.tflite";
    private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/RedProp.tflite";
    private static final String[] LABELS = {
            "Prop",
    };
    private TfodProcessor tfod;
    private VisionPortal visionPortal;
    private ExposureControl myExposureControl;

    // variable for autonomous
    public static int driveToProp = 1200, turnDistance = 300, calibratedCenter = 350,
            measuredVisionError = 40;

    // camera values, for calibrating to lighting and etc
    public static int exposure = 30, gain, whiteBalance, focus, ptz;

    @Override
    public void runOpMode() throws InterruptedException {

    }
}
