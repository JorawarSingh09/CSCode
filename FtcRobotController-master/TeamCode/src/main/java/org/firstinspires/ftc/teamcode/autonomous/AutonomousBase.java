package org.firstinspires.ftc.teamcode.autonomous;

public interface AutonomousBase {
    /**
     * If no recognition found place pixel on center tape and park.
     */
    public void defaultDropAndPark();

    /**
     * Strafe and place pixel on line
     */
    public void dropPixel();

    /**
     * drop a pixel into parking area by shaking
     */
    public void shakePixel();

}
