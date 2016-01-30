package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import android.hardware.SensorManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Iam on 10/30/2015.
 */
public abstract class FourWheelDriveBaseAuton extends LinearOpMode{

    private DcMotor motor1, motor2, motor3, motor4;
    private SensorManager sensorManager;

    protected double speedRatio = 1;

    HashMap<String, Servo> servoHashMap = new HashMap<String, Servo>(12); //for if we have servos we need in Auton

    private Servo armServoLeft, armServoRight;
    private Servo climArmLeft, climArmRight;
    private Servo autoServo;

    public static final long NANO_TO_MILLI = 1000000;





    public FourWheelDriveBaseAuton() {}

    public void initServos() {
        climArmLeft = hardwareMap.servo.get("climberL");
        climArmRight = hardwareMap.servo.get("climberR");
        autoServo = hardwareMap.servo.get("autoServo");
        servoHashMap.put("climberL", climArmLeft);
        servoHashMap.put("climberR", climArmRight);
        servoHashMap.put("autoServo", autoServo);
    }

    protected void initSensorManager() {
//        sensorManager = (SensorManager) this.getBaseContext().getSystemService(Context.SENSOR_SERVICE);
    }

    public void setMotorNames(String motorName1, String motorName2, String motorName3, String motorName4) {
        this.motor1 = getMotor(motorName1);
        this.motor2 = getMotor(motorName2);
        this.motor3 = getMotor(motorName3);
        this.motor4 = getMotor(motorName4);
    }


    /**
     * Stop all motors on the robot using @link {#setPowerAll(double motorPower) setPowerAll}.
     */
    public void stopMotors() {
        setPowerAll(0);
    }

    /**
     * Takes four parameters to set each motor separately. Use is highly discouraged.
     *
     * Set the powers of all of the motors with one function call. Don't use this function
     * unless needed, as most maneuvers will not require setting each motor seperately.
     *
     * @param power1 The power to pass to motor1, should be a double between -1.0 and 1.0
     * @param power2 The power to pass to motor2, should be a double between -1.0 and 1.0
     * @param power3 The power to pass to motor3, should be a double between -1.0 and 1.0
     * @param power4 The power to pass to motor4, should bea double between -1.0 and 1.0
     */
    public void setPower(double power1, double power2, double power3, double power4) {
        motor1.setPower(speedRatio * power1);
        motor2.setPower(speedRatio * power2);
        motor3.setPower(speedRatio * power3);
        motor4.setPower(speedRatio * power4);
    }

    /**
     * Takes two parameters to set the power of two motors on each side.
     *
     * The preferred method of setting motors for drive functions, used mostly for
     * turning the robot or as a more inefficient way to move forward or backwards.
     *
     * @param leftSidePower The power to pass to the left side drive motors, should be a double between -1.0 and 1.0
     * @param rightSidePower The power to pass to the right side drive motors, should be a double between -1.0 and 1.0
     */
    public void setPowerSides(double leftSidePower, double rightSidePower) {
        this.setPower(leftSidePower, leftSidePower, rightSidePower, rightSidePower);
    }

    /**
     * Takes one parameter to set the power of all motors.
     *
     * The preferred method to drive forwards or backwards. For stop, use @link #stop()
     * @param motorPower The power to pass to all of the motors, should be a double between -1.0 and 1.0.
     */
    public void setPowerAll(double motorPower) {
        this.setPower(motorPower, motorPower, motorPower, motorPower);
        telemetry.addData("Motors", "Power Set to " + motorPower);
    }

    /**
     * Get the Y value on controller 1's left joystick
     * @return The left joystick's y value, from the driver controller.
     */
    public float getJoy1LeftY() {
        return Math.abs(gamepad1.left_stick_y) > .25 ? gamepad1.left_stick_y : 0;
    }

    /**
     * Get the Y value on controller 1's right joystick
     * @return The right joystick's y value, from the driver controller.
     */
    public float getJoy1RightY() {
        return Math.abs(gamepad1.right_stick_y) > .25 ? gamepad1.right_stick_y : 0;
    }

    public void setPowerFromJoy() {
        setPowerSides(getJoy1LeftY(), -getJoy1RightY());
    }

    /**Get a motor from the hardware map
     *
     * The robotcontroller will setup OpModes with certain motor names
     * and save them to hardwareMap. This function is used to make the
     * code cleaner for the contructor.
     *
     * @param name The name of the motor we are getting
     * @return The motor with the specified name.
     */
    private DcMotor getMotor(String name) {
        return hardwareMap.dcMotor.get(name);
    }

    public long toNano(long val) {
        return val * 1000000000;
    }

    protected void stopFor(long millis) {
        long startTime = System.nanoTime();
        while((System.nanoTime() - startTime) / NANO_TO_MILLI < millis);
        telemetry.addData("done with wait", "said no one ever");
    }

    public void setSpeedRatio(double speedRatio) {
        this.speedRatio = speedRatio;
    }

    public double getSpeedRatio() {return speedRatio;}

    //Copyright Laura
    protected String executeScript(String scriptText) throws InterruptedException { //defaults to time param
        String[] typeParameters = scriptText.split(" ");
        String type = typeParameters[0];
        if(type.equalsIgnoreCase("drive")) {
            telemetry.addData("Drive?", "Drive!");
            if(typeParameters.length != 4 && typeParameters.length != 6) { //type, [powers], time
                telemetry.addData("psych", "those the wrong paramatahs");
                return "badParams";
            }
            else if(typeParameters.length == 4) {
                setPowerSides(Double.parseDouble(typeParameters[1]), Double.parseDouble(typeParameters[2]));
                stopFor(Integer.parseInt(typeParameters[3]));
            }
            else {
                setPower(Double.parseDouble(typeParameters[1]), Double.parseDouble(typeParameters[2]),
                        Double.parseDouble(typeParameters[3]), Double.parseDouble(typeParameters[4]));
                stopFor(Integer.parseInt(typeParameters[5]));
            }
        } else if(type.equalsIgnoreCase("servo")) {
            if(typeParameters.length == 3) {
                servoHashMap.get(typeParameters[1]).setPosition(Double.parseDouble(typeParameters[2]));
            }
        }
        else {
            telemetry.addData("Type", "Incorrect");
            return "Wrong Type";
        }
        return "done";
    }

    protected String executeScript(String scriptText, String endCon) throws InterruptedException {
        String[] typeParameters = scriptText.split(" ");
        String[] endCons = endCon.split(" ");
        if(endCons.length < 2) {
            return "not enough params on endcon";
        }
        String type = typeParameters[0];
        String endConType = endCons[0];

        if(type.equalsIgnoreCase("drive")) {
            telemetry.addData("Drive?", "Drive!");
            if(typeParameters.length != 3 && typeParameters.length != 5) { //type, [powers]
                telemetry.addData("psych", "those the wrong paramatahs");
                return "badParams";
            }
            else if(typeParameters.length == 3) {
                setPowerSides(Double.parseDouble(typeParameters[1]), Double.parseDouble(typeParameters[2]));
            }
            else {
                setPower(Double.parseDouble(typeParameters[1]), Double.parseDouble(typeParameters[2]),
                        Double.parseDouble(typeParameters[3]), Double.parseDouble(typeParameters[4]));

            }
        } else {
            telemetry.addData("Type", "Incorrect");
            return "Wrong Type";
        }


        //Waiting Code

        if(endConType.equalsIgnoreCase("time")) {
            stopFor(Integer.parseInt(endCons[1]));
        } else if(endConType.equalsIgnoreCase("encoder")) {
            //Add Encoder Code here
        } else if(endConType.equalsIgnoreCase("gyro")) {
            //Add Gyro Code here
        }
        return "done";
    }
}
