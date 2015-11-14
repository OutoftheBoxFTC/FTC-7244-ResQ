package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Iam on 10/5/2015.
 */
public class SimpleTeleop extends FourWheelDriveBase{
    private boolean lastPushedLeftBumper, lastPushedRightBumper;
    @Override
    public void loop() {
        if(lastPushedLeftBumper || lastPushedRightBumper) {
            super.setSpeedRatio(0.5);
        } else {
            super.setSpeedRatio(1.0);
        }

        setPowerFromJoy();

        lastPushedLeftBumper = getLeftBumper();
        lastPushedRightBumper = getRightBumper();
    }

    @Override
    public void start() {}

    @Override
    public void stop() {super.stop();}

    @Override
    public void init() {
        setMotorNames("motor_1", "motor_2", "motor_3", "motor_4");
    }

    public SimpleTeleop(){}
}
