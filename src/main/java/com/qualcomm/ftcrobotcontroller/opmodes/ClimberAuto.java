package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.Servo;

public class ClimberAuto extends FourWheelDriveBaseAuton {
   private Servo climber;
   private Servo releaseServoLeft, releaseServoRight;

    @Override
    public void runOpMode() throws InterruptedException {
        climber = hardwareMap.servo.get("autoServo");
        climber.setPosition(1.0);

        releaseServoLeft = hardwareMap.servo.get("releaseL");
        releaseServoRight = hardwareMap.servo.get("releaseR");
        releaseServoLeft.setPosition(0);
        releaseServoRight.setPosition(1);

        super.setSpeedRatio(.5);

        setMotorNames("motor_1", "motor_2", "motor_3", "motor_4");

        waitForStart();

        setPowerSides(-1.0, 0);
        stopFor(1500);
        setPowerSides(-1.0, 1.0);
        stopFor(4300);
        setPowerSides(-1.0, 0);
        stopFor(1250);
        setPowerSides(-1.0, 1.0);
        stopFor(550);
        setPowerSides(0 , 0);
        climber.setPosition(.85);
    }
}