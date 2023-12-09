package org.firstinspires.ftc.teamcode.autonomous.compVision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.CenterStageRobot;
import org.firstinspires.ftc.teamcode.autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.autonomous.TFBase;

public class TeamPropMovements extends LinearOpMode implements AutonomousBase {

    CenterStageRobot myRobot;
    public TeamPropMovements(CenterStageRobot myRobot){
        this.myRobot = myRobot;
    }
    @Override
    public void runOpMode() throws InterruptedException {

    }

    @Override
    public void defaultDropAndPark() {

    }

    @Override
    public void dropPixelCenter() {
        myRobot.driveForward();
        sleep(1550);
        myRobot.driveStop();
        myRobot.pickupPosition();
        myRobot.driveStop();
        sleep(100);
        myRobot.driveBack();
        sleep(200);
        myRobot.driveStop();
        myRobot.closeClaw();
        myRobot.drivePosition();
    }

    @Override
    public void dropPixelLeft() {
        myRobot.driveForward();
        sleep(1600);
        myRobot.turnLeft();
        sleep(800);
        myRobot.driveForward();
        sleep(150);
        myRobot.driveStop();
        myRobot.pickupPosition();
        myRobot.driveBack();
        sleep(450);
        myRobot.driveStop();
        myRobot.closeClaw();
        myRobot.drivePosition();
    }

    @Override
    public void dropPixelRight() {
        myRobot.driveForward();
        sleep(1550);
        myRobot.turnRight();
        sleep(800);
        myRobot.driveForward();
        sleep(150);
        myRobot.driveStop();
        myRobot.pickupPosition();
        myRobot.driveBack();
        sleep(450);
        myRobot.driveStop();
        myRobot.closeClaw();
        myRobot.drivePosition();
    }

    @Override
    public void shakePixel() {
        myRobot.driveBack();
        sleep(1100);
        myRobot.driveStop();
        myRobot.strafeLeft();
        sleep(4000);
        myRobot.driveForward();
        sleep(2000);
        myRobot.strafeLeft();
        sleep(2000);
        myRobot.driveStop();
    }
}
