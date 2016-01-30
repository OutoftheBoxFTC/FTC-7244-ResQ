package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Iam on 1/9/2016.
 */
public class PlowAuto extends FourWheelDriveBaseAuton{

    @Override
    public void runOpMode() throws InterruptedException {
        setMotorNames("motor_1", "motor_2", "motor_3", "motor_4");
        waitForStart();
        setPowerSides(-1.0, 1.0);
        stopFor(2000);
        setPowerSides(0 , 0);
    }
}
