package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Iam on 10/30/2015.
 */
public class FourWheelSimpleAutonomous extends FourWheelDriveBaseAuton{

    @Override
    public void runOpMode() throws InterruptedException {
        setMotorNames("motor_1", "motor_2", "motor_3", "motor_4");
        waitForStart();
        this.resetStartTime();
        setPowerSides(1.0, -1.0);
        while(this.getRuntime() < 3);
        setPowerSides(0, 0);
    }
}
