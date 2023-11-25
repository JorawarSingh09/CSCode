package org.firstinspires.ftc.teamcode.Mechanims;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LinearSlides {
    //Motors
    private DcMotor leftLinearSlide;
    private DcMotor rightLinearSlide;

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

    /**
    Change Position
     INPUT: pos - change we want from current position
     Move the linear slide up or down from current position
     */
    public void changePosition(int pos){
        // do nothing if linear slides are already at the bottom
        if(leftLinearSlide.getCurrentPosition() < 20 && pos < 0){
            return;
        }
        double power = 0.4;
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

        // Switch back to RUN_USING_ENCODER mode
//        leftLinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightLinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void bottomPosition(){
        double power = 0.4;
        leftLinearSlide.setTargetPosition(0);
        rightLinearSlide.setTargetPosition(0);

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
    }

    public void topPosition(){
        double power = 0.8;
        leftLinearSlide.setTargetPosition(1550);
        rightLinearSlide.setTargetPosition(1550);

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
    }
    public void manualDrive(float left, float right){
        leftLinearSlide.setPower(left);
        rightLinearSlide.setPower((right));
    }
    public int[] getPosition() {
        int[] pos = {leftLinearSlide.getCurrentPosition(),
                    rightLinearSlide.getCurrentPosition()};
        return pos;
    }
}
