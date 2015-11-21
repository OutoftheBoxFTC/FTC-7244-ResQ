package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Iam on 11/20/2015.
 */
public class RunForever extends FourWheelDriveBaseAuton {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        setPowerSides(1.0, -1.0);
        while(true);
    }
}
