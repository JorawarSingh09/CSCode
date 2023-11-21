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

    public void setPosition(int pos){
        double power = 0.5;
        leftLinearSlide.setTargetPosition(pos);
        rightLinearSlide.setTargetPosition(pos);

        leftLinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftLinearSlide.setPower(power);
        rightLinearSlide.setPower(power);

        while (leftLinearSlide.isBusy() && rightLinearSlide.isBusy()) {
            // do some stuff here i guess
        }

        // Stop the motors after reaching the position
        leftLinearSlide.setPower(0);
        rightLinearSlide.setPower(0);

        // Switch back to RUN_USING_ENCODER mode
        leftLinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void manualDrive(double left, double right){
        leftLinearSlide.setPower(left);
        rightLinearSlide.setPower((right));
    }
    public int getPosition() {
        // average between two slides, gets us the guess at a position
        return (leftLinearSlide.getCurrentPosition() +
                rightLinearSlide.getCurrentPosition()) / 2;
    }
}
