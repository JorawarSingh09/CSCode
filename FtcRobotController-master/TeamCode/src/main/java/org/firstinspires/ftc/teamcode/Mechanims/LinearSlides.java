package org.firstinspires.ftc.teamcode.Mechanims;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class LinearSlides {
    //Motors
    private DcMotor leftLinearSlide;
    private DcMotor rightLinearSlide;

    private double Kp = 0.1; // Proportional coefficient
    private double Ki = 0.01; // Integral coefficient
    private double Kd = 0.05; // Derivative coefficient
    ElapsedTime timer = new ElapsedTime();
    private double integralSum = 0;
    private double lastError = 0;

    public LinearSlides(HardwareMap hm){
        leftLinearSlide = hm.get(DcMotor.class, "l_linear_slides");
        rightLinearSlide = hm.get(DcMotor.class, "r_linear_slides");

        //direction of motors
        leftLinearSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        rightLinearSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set the motors to run with encoders.
        leftLinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Reset encoders
        leftLinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Hold position
        leftLinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void manualDrive(float left, float right){
        leftLinearSlide.setPower(left);
        rightLinearSlide.setPower((right));
    }

    public void changePosition(int pos){
        // do nothing if linear slides are already at the bottom
        if(leftLinearSlide.getCurrentPosition() < 20 && pos < 0){
            return;
        }
        setPosition(leftLinearSlide.getCurrentPosition() + pos, 0.4);
    }

    public void setPosition(int pos, double power){

        leftLinearSlide.setTargetPosition(leftLinearSlide.getCurrentPosition() + pos);
        rightLinearSlide.setTargetPosition(rightLinearSlide.getCurrentPosition() + pos);

        leftLinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftLinearSlide.setPower(power);
        rightLinearSlide.setPower(power);

        while (leftLinearSlide.isBusy() && rightLinearSlide.isBusy()) {
            // do some stuff here i guess
        }

        // Stop the motors after reaching the position
        leftLinearSlide.setPower(0.1);
        rightLinearSlide.setPower(0.1);

        leftLinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void bottomPosition(){
        setPosition(0, 0.4);
    }

    public void topPosition(){
        setPosition(1500, 0.6);
    }

    public int[] getPosition() {
        int[] pos = {leftLinearSlide.getCurrentPosition(),
                    rightLinearSlide.getCurrentPosition()};
        return pos;
    }
}
