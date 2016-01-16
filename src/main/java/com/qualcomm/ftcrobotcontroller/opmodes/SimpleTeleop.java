package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Iam on 10/5/2015.
 */
public class SimpleTeleop extends FourWheelDriveBase{
    private boolean drLastPushedLeftBumper, drLastPushedRightBumper;
    private boolean opLastPushedLeftBumper, opLastPushedRightBumper;
    private boolean lastPushedBButton;
    private boolean lastPushedOpBButton, lastPushedOpXButton, lastPushedOpAButton;
    private boolean pushedOpAButton, pushedOpBButton, pushedOpXButton;


    private DcMotor hangLeft, hangRight;

    private Servo releaseServoLeft, releaseServoRight;
    private Servo climArmLeft, climArmRight;
    private Servo autoServo;

    public long startTime;
    public static final long NANO_TO_MILLI = 1000000;

    protected void stopFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            telemetry.addData("psych", "Error in wait");
        }
    }


    public double getJoy2LeftY() {
        return gamepad2.left_stick_y;
    }

    public double getJoy2RightY() {
        return gamepad2.right_stick_y;
    }

    public void setHangingMotorsFromJoy() {
        hangLeft.setPower(getJoy2LeftY());
        hangRight.setPower(getJoy2RightY());
    }


    private void updateButtons() {
        drLastPushedLeftBumper = getLeftBumper();
        drLastPushedRightBumper = getRightBumper();

        opLastPushedLeftBumper = getOpLeftBumper();
        opLastPushedRightBumper = getOpRightBumper();

        lastPushedBButton = getJoyButtonB();

        pushedOpBButton = getOpJoyButtonB();
        pushedOpXButton = getOpJoyButtonX();
        pushedOpAButton = getOpJoyButtonA();

    }


    @Override
    public void start() {
        startTime = System.nanoTime();
        //  climArmLeft.setPosition(0);
        //  climArmRight.setPosition(0);
    }

    @Override
    public void stop() {super.stop();}

    @Override
    public void init() {
        setDriveMotorNames("motor_1", "motor_2", "motor_3", "motor_4");

        hangLeft = super.getMotor("hangL");
        hangRight = super.getMotor("hangR");

        releaseServoLeft = hardwareMap.servo.get("releaseL");
        releaseServoRight = hardwareMap.servo.get("releaseR");
        releaseServoLeft.setPosition(0);
        releaseServoRight.setPosition(1);


        autoServo = hardwareMap.servo.get("autoServo");
        autoServo.setPosition(1.0);

        climArmLeft = hardwareMap.servo.get("climbL");
        climArmRight = hardwareMap.servo.get("climbR");
        climArmLeft.setPosition(0);
        climArmRight.setPosition(1);
    }

    public SimpleTeleop(){}



    @Override
    public void loop() {
        updateButtons();
        if (drLastPushedLeftBumper || drLastPushedRightBumper) {
            super.setSpeedRatio(0.5);
        } else {
            super.setSpeedRatio(1.0);
        }

        if (!(opLastPushedLeftBumper && opLastPushedRightBumper)) {
            startTime = System.nanoTime();
        }

        if (((System.nanoTime() - startTime) / NANO_TO_MILLI) > 500) {
            releaseServoLeft.setPosition(1);
            releaseServoRight.setPosition(0);
        }

        if (pushedOpAButton && !lastPushedOpAButton) {
            autoServo.setPosition(autoServo.getPosition() < .5 ? 1 : .25);
        }

        if (pushedOpBButton && !lastPushedOpBButton) {
            climArmLeft.setPosition(autoServo.getPosition() < .5 ? 1 : .25);
        }

        if (pushedOpXButton && !lastPushedOpXButton) {
            climArmRight.setPosition(autoServo.getPosition() < .5 ? 1 : .25);
        }

        setPowerFromJoy();
        setHangingMotorsFromJoy();
        lastPushedOpAButton = pushedOpAButton;
        lastPushedOpBButton = pushedOpBButton;
        lastPushedOpXButton = pushedOpXButton;
    }
}

