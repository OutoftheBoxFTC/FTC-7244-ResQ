package com.ftc7244.resq;

public class FourWheelSimpleDrive extends FourWheelDriveBase {

    /**
     * This constructor will setup the motors with the
     * names for the motors. We will setup the motors
     * with the same motor names on the OpMode on the
     * RobotController.
     */
    public FourWheelSimpleDrive() {
        super("motor1", "motor2", "motor3", "motor4");
    }

    @Override
    public void start() {

    }

    @Override
    /**
     *
     */
    public void loop() {
        setPowerSides(getJoy1LeftY(), getJoy1RightY());
    }

    @Override
    public void init() {}
}

//UNDER BUILD INTERMEDIATES
