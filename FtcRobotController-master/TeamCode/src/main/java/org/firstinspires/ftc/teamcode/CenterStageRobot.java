package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GrabberArm;
import org.firstinspires.ftc.teamcode.subsystems.GrabberState;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.subsystems.MechanismState;

public class CenterStageRobot {
    //contains all the code for interacting with the robots functions
    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime mechTimer = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private LinearSlides linearSlides;
    private GrabberArm grabberArm;
    private Servo planeLauncher;
    private MechanismState mechanismState = MechanismState.INIT;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    private final double LOW_SPEED = 0.3;
    private final double MEDIUM_SPEED = 0.6;
    private final double HIGH_SPEED = 1.0;
    private double currentSpeedLimit = HIGH_SPEED;

    /**
     * Initialises the robot with all its motors
     * @param hardwareMap
     * @param telemetry
     */
    public CenterStageRobot(HardwareMap hardwareMap, Telemetry telemetry){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        setDrive();
        planeLauncher = hardwareMap.get(Servo.class, "plane_launcher");
        linearSlides = new LinearSlides(hardwareMap);
        grabberArm = new GrabberArm(hardwareMap);
    }

    /**
     * update Telemetry
     */
    private void telemetry(){
        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Linear Slide Position left/right: ", "%d, %d",
                linearSlides.getPosition()[0], linearSlides.getPosition()[1]);
        telemetry.addData("Arm Position: ", "%d", grabberArm.getArm());
        telemetry.addData("Wrist Position: ", "%4.2f", grabberArm.getWrist());
        telemetry.addData("Claw Position: ", "%4.2f", grabberArm.getClaw());
        telemetry.update();
    }

    /**
     * Updates values on the robot such as telemetry
     */
    public void update(){
        telemetry();
    }

    /**
     * Initialize the hardware variables. Note that the strings used here must correspond to the
     * names assigned during the robot configuration step on the DS or RC devices.
     */
    private void setDrive(){
        //
        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    /**
     *
     * @param axial
     * @param lateral
     * @param yaw
     * @return
     */
    public double[] drive(double axial, double lateral, double yaw){
        double max;
//        drivePosition();
        double leftFrontPower = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower = axial - lateral + yaw;
        double rightBackPower = axial + lateral - yaw;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        // check state of mechanism and set speed limit
        switch (mechanismState) {
            case PICKUP:
                currentSpeedLimit = MEDIUM_SPEED;
            case TOP_POSITION:
                currentSpeedLimit = LOW_SPEED;
            case DROP_POSITION:
                currentSpeedLimit = LOW_SPEED;
            default:
                currentSpeedLimit = HIGH_SPEED;

        }

        leftFrontDrive.setPower(leftFrontPower * currentSpeedLimit);
        rightFrontDrive.setPower(rightFrontPower * currentSpeedLimit);
        leftBackDrive.setPower(leftBackPower * currentSpeedLimit);
        rightBackDrive.setPower(rightBackPower * currentSpeedLimit);
        double[] drivePower = {leftFrontPower, rightFrontPower, leftBackPower, rightBackPower};
        return drivePower;
    }

    /**
     * Drive forwards for targetSeconds
     */
    public void driveForward(){
        drive(1, 0 ,0);
    }

    /**
     * Drive backwards for targetSeconds
     */
    public void driveBack(){
        drive(-1, 0, 0);
    }

    /**
     * Rotate left for targetSeconds
     */
    public void turnRight(){
        drive(0, 0, 1);
    }

    /**
     * rotate right for targetSeconds
     */
    public void turnLeft(){
        drive(0, 0, -1);
    }

    /**
     * Strafe left for targetSeconds
     */
    public void strafeLeft(){
        drive(0, -1, 0);
    }

    /**
     * Strafe right for targetSeconds
     */
    public void strafeRight(){
        drive(0, 1, 0);
    }

    /**
     * Stop drive
     */
    public void driveStop(){
        drive(0, 0, 0);
    }

    /**
     * position of robot before game has begun
     */
    public void startPosition(){
        driveStop();
        mechanismState = MechanismState.INIT;
        //set Arm position to bottom
        grabberArm.setArmPosition(0);
        // set Wrist to fold up
        grabberArm.setWristPosition(1);
        //set Claw to open
        grabberArm.closeClaw();
        // linear slides pos
        linearSlides.bottomPosition();
    }

    /**
     * Mechanism position when drive,
     */
    public void drivePosition(){
        driveStop();
        if(mechanismState == MechanismState.PICKUP && grabberArm.getGrabberState() == GrabberState.CLOSED){
            mechanismState = MechanismState.DRIVE;
            grabberArm.setArmPosition(100);
            grabberArm.setWristPosition(0.3);
        }
    }

    /**
     * mechanism position when you need to pickup the pixel
     */
    public void pickupPosition(){
        driveStop();
        if(mechanismState == MechanismState.PICKUP) return;
        mechanismState = MechanismState.PICKUP;

        grabberArm.setArmPosition(200);
        grabberArm.setWristPosition(0);
        linearSlides.bottomPosition();
        grabberArm.setArmPosition(20);
        grabberArm.openClaw();
    }

    /**
     * Mechanism position between drop and pickup
     */
    public void topPosition(){
        driveStop();
        if(mechanismState == MechanismState.TOP_POSITION) return;
        mechanismState = MechanismState.TOP_POSITION;

        grabberArm.setWristPosition(0);
        grabberArm.closeClaw();
        grabberArm.setArmPosition(520);
        linearSlides.topPosition();
    }

    /**
     * mechanism position for dropping pixel
     */
    public void dropPosition(){
        driveStop();
        if(mechanismState == MechanismState.DROP_POSITION) return;
        mechanismState = MechanismState.DROP_POSITION;
        grabberArm.setWristPosition(0.25);

        grabberArm.closeClaw();

        grabberArm.setArmPosition(810);

        linearSlides.topPosition();
    }

    /**
     * Raise arms and put them in position for climb
     */
    public void raiseHooks(){
        driveStop();
        linearSlides.climbPosition() ;
    }

    /**
     * lower climb so that robot climbs up
     */
    public void climb(){
        driveStop();
        linearSlides.climb(1);
    }

    /**
     * for adjusting linear slide pickup and drop off
     */
    public void adjustSlides(int pos){
        driveStop();
        linearSlides.changePosition(pos);
    }

    /**
     * launch plane
     */
    public void launchPlane(){
        driveStop();
        planeLauncher.setPosition(1);
    }

    /**
     * Reset plane launcher
     */
    public void resetLauncher(){
        driveStop();
        planeLauncher.setPosition(0);
    }

    /**
     * open claw
     */
    public void openClaw(){
        driveStop();
        grabberArm.openClaw();
    }

    /**
     * close claw
     */
    public void closeClaw(){
        driveStop();
        grabberArm.closeClaw();
    }
}
