package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Iam on 10/30/2015.
 */
public class BotBuildesSimpleAutonomous extends OpMode {
    DcMotor motor1, motor2, motor3;
    Servo servo1, servo2;

    @Override
    public void init() {
        motor1 = hardwareMap.dcMotor.get("motor_1"); //assign motor one to a variable
        motor2 = hardwareMap.dcMotor.get("motor_2");
        motor3 = hardwareMap.dcMotor.get("motor_3");
        servo1 = hardwareMap.servo.get("servo_1");
        servo2 = hardwareMap.servo.get("servo_2");

    }

    @Override
    public void loop() {
        motor1.setPower(gamepad1.left_stick_y); //read the joystick and set the power
        motor2.setPower(-gamepad1.right_stick_y);//reverse the power
        motor3.setPower(gamepad2.right_stick_y);

        double armPos1, armPos2;
        if(gamepad2.a) {
            armPos1 = 0.0;
            armPos2 = 1.0;
            telemetry.addData("Button:", "pressed");
        } else {
            armPos1 = 1.0;
            armPos2 = 0.0;
            telemetry.addData("Button:", "notpressed");
        }
        servo1.setPosition(armPos1);
        servo2.setPosition(armPos2);
    }
}


