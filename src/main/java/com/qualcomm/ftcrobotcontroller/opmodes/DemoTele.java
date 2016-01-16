package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Iam on 11/29/2015.
 */
public class DemoTele extends SimpleTeleop {
    @Override
    public void setPower(double power1, double power2, double power3, double power4) {
        motor1.setPower(speedRatio * power1);
        motor2.setPower(speedRatio * power2);
        motor3.setPower(speedRatio * power3);
        motor4.setPower(speedRatio * power4);
    }
}
