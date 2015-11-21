package com.qualcomm.ftcrobotcontroller.opmodes;

public class FourWheelSimpleDrive extends FourWheelDriveBase {

    /**
     * This constructor will setup the motors with the
     * names for the motors. We will setup the motors
     * with the same motor names on the OpMode on the
     * RobotController.
     */
    public FourWheelSimpleDrive() {}

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
    public void init() { setMotorNames("motor_1", "motor_2", "motor_3", "motor_4"); }
}

//UNDER BUILD INTERMEDIATES
