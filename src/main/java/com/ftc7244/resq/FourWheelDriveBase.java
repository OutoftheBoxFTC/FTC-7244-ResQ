package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public abstract class FourWheelDriveBase extends OpMode {

    protected DcMotor motor1, motor2, motor3, motor4;

    private double speedRatio = 1;

    public FourWheelDriveBase() {}

    public void setMotorNames(String motorName1, String motorName2, String motorName3, String motorName4) {
        this.motor1 = getMotor(motorName1);
        this.motor2 = getMotor(motorName2);
        this.motor3 = getMotor(motorName3);
        this.motor4 = getMotor(motorName4);
    }

    /**
     * Stop all motors on the robot using @link {#setPowerAll(double motorPower) setPowerAll}.
     */
    @Override
    public void stop() {
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
     * @param power4 The power to pass to motor4, should be a double between -1.0 and 1.0
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
        return gamepad1.left_stick_y;
    }

    /**
     * Get the Y value on controller 1's right joystick
     * @return The right joystick's y value, from the driver controller.
     */
    public float getJoy1RightY() {
        return gamepad1.right_stick_y;
    }


    public void setPowerFromJoy() {
        setPowerSides(getJoy1LeftY(), -getJoy1RightY());
    }

    public boolean getJoyButtonY() {return gamepad1.y;}

    public boolean getLeftBumper() {return gamepad1.left_bumper;}

    public boolean getRightBumper() {return gamepad1.right_bumper;}

    public void setSpeedRatio(double speedRatio) {
        if(speedRatio < 1.0 && speedRatio > -1.0) this.speedRatio = speedRatio;
    }

    public double getSpeedRatio() {return speedRatio;}
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
}
