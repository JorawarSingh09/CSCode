package org.firstinspires.ftc.teamcode.Mechanims;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class GrabberArm {
    private Servo claw = null;
    private Servo wrist = null;
    private DcMotor arm = null;

    public GrabberArm(HardwareMap hm){
        claw = hm.get(Servo.class, "claw");
        wrist = hm.get(Servo.class, "wrist");
        arm = hm.get(DcMotor.class, "arm");

        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void openClaw(){
        claw.setPosition(0);
    }

    public void closeClaw(){
        claw.setPosition(1);
    }

    public void moveWrist(double pos){
        wrist.setPosition(wrist.getPosition() + pos);
    }

    public void changeArmPosition(int pos){
        //if arm is at bottom dont move
        if(pos < 0 && arm.getCurrentPosition() < 0) return;

        double power = 0.6;
        arm.setTargetPosition(arm.getCurrentPosition() + pos);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm.setPower(power);

        while (arm.isBusy()) {
            // do some stuff here i guess
        }

        // Stop the motors after reaching the position
        arm.setPower(0.1);

        // Switch back to RUN_USING_ENCODER mode
//        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setPosition(int pos, double power){

        arm.setTargetPosition(arm.getCurrentPosition() + pos);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm.setPower(power);

        while (arm.isBusy()) {
            // do some stuff here i guess
        }

        // Stop the motors after reaching the position
        arm.setPower(0.1);
    }
    public void drivePosition(){
        wrist.setPosition(0);

        //how fast the arm moves
        double power = 0.6;
        arm.setTargetPosition(90);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(power);

        while (arm.isBusy()) {
            // do some stuff here i guess
        }

        // Stop the motors after reaching the position
        arm.setPower(0.1);
    }
    public void pickupPosition(){
        wrist.setPosition(0);
        openClaw();
        //how fast the arm moves
        double power = 0.6;
        arm.setTargetPosition(0);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(power);

        while (arm.isBusy()) {
            // do some stuff here i guess
        }

        // Stop the motors after reaching the position
        arm.setPower(0.1);
    }
    public void topPosition(){
       // At top: arm 511, wrist 0
        wrist.setPosition(0);
        claw.setPosition(0);

        //how fast the arm moves
        double power = 0.6;
        arm.setTargetPosition(511);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(power);

        while (arm.isBusy()) {
            // do some stuff here i guess
        }

        // Stop the motors after reaching the position
        arm.setPower(0.1);
    }
    public void dropPosition(){
        // at drop: arm 834, wrist 0.5
        wrist.setPosition(0.5);

        //how fast the arm moves
        double power = 0.6;
        arm.setTargetPosition(835);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm.setPower(power);

        while (arm.isBusy()) {
            // do some stuff here i guess
        }

        // Stop the motors after reaching the position
        arm.setPower(0.1);

    }

    public void startPosition(){
        // at drop: arm 834, wrist 0.5
        wrist.setPosition(1);
        claw.setPosition(0);

        //how fast the arm moves
        double power = 0.6;
        arm.setTargetPosition(0);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm.setPower(power);

        while (arm.isBusy()) {
            // do some stuff here i guess
        }

        // Stop the motors after reaching the position
        arm.setPower(0.1);

    }
    public int getArm(){
        return arm.getCurrentPosition();
    }

    public double getWrist(){
        return wrist.getPosition();
    }

    public double getClaw(){
        return claw.getPosition();
    }
}
