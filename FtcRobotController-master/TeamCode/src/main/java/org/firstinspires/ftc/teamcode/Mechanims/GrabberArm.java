package org.firstinspires.ftc.teamcode.Mechanims;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class GrabberArm {
    private Servo claw = null;
    private Servo wrist = null;
    private DcMotor arm = null;
    private GrabberState grabberState = GrabberState.CLOSED;

    private double Kp = 0.2; // Proportional coefficient
    private double Ki = 0; // Integral coefficient
    private double Kd = 0; // Derivative coefficient
    private double Kf = 0;

    private double integral, previous_error;

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
//        claw.setPosition(1);
//        grabberState = GrabberState.OPEN;
    }

    public void closeClaw(){
//        claw.setPosition(0);
//        grabberState = GrabberState.CLOSED;
    }

    public GrabberState getGrabberState(){
        return grabberState;
    }

    public void setWristPosition(double pos){
//        wrist.setPosition(pos + 0.36);
    }

    public void changeArmPosition(int pos){
        //if arm is at bottom dont move
        if(pos < 0 && arm.getCurrentPosition() < 0) return;

        double power = 0.3;
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

    public void setArmPosition(int targetPosition) {
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        integral = 0; // Reset integral
        previous_error = 0; // Reset previous error

        while (Math.abs(arm.getCurrentPosition() - targetPosition) > 10) {
            double error = targetPosition - arm.getCurrentPosition();
            integral += error; // Integral is sum of errors
            double derivative = error - previous_error;

            // PID formula
            double power = Kp * error + Ki * integral + Kd * derivative + Kf * targetPosition;

            // Limit the power to max/min motor power if necessary
            power = Math.max(-1, Math.min(1, power));

            arm.setPower(power);

            previous_error = error; // Update previous error

            // Some delay to prevent too frequent updates
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Stop the motors after reaching the position
        arm.setPower(0);
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
