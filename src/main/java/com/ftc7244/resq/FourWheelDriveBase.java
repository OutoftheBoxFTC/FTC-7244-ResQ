package com.ftc7244.resq;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Written By: Out of the Box on 9/14/2015
 */
public abstract class FourWheelDriveBase extends OpMode {

    private DcMotor motor1, motor2, motor3, motor4;

    public FourWheelDriveBase(String motorName1, String motorName2, String motorName3, String motorName4) {
        this.motor1 = getMotor(motorName1);
        this.motor2 = getMotor(motorName2);
        this.motor3 = getMotor(motorName3);
        this.motor4 = getMotor(motorName4);
    }

    @Override
    public void stop() {
        setPowerAll(0);
    }

    public void setPower(double power1, double power2, double power3, double power4) {
        motor1.setPower(power1);
        motor2.setPower(power2);
        motor3.setPower(power3);
        motor4.setPower(power4);
    }

    public void setPowerSides(double leftSidePower, double rightSidePower) {
        this.setPower(leftSidePower, leftSidePower, rightSidePower, rightSidePower);
    }

    public void setPowerAll(double motorPower) {
        this.setPower(motorPower, motorPower, motorPower, motorPower);
    }

    public float getJoy1LeftY() {
        return gamepad1.left_stick_y;
    }

    public float getJoy1RightY() {
        return gamepad1.right_stick_y;
    }

    private DcMotor getMotor(String name) {
        return hardwareMap.dcMotor.get(name);
    }
}
