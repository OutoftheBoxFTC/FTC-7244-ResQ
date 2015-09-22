package com.ftc7244.resq;

/**
 * Created by Iam on 9/14/2015.
 */
public class FourWheelSimpleDrive extends FourWheelDriveBase {

    public FourWheelSimpleDrive() {
        super("motor1", "motor2", "motor3", "motor4");
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        setPowerSides(getJoy1LeftY(), getJoy1RightY());
    }
}
