package com.qualcomm.ftcrobotcontroller.opmodes;

import java.io.IOException;

/**
 * Created by Iam on 11/15/2015.
 */
public class ScriptReaderAuton extends FourWheelDriveBaseAuton {
 //   private ScriptReader scriptReader;

    @Override
    public void setPower(double power1, double power2, double power3, double power4) {
       super.setPower(-power1, -power2, power3, power4);
    }

    @Override
    public void runOpMode() throws InterruptedException {
       // try{scriptReader = ScriptReader.buildScriptReader("");}
       // catch (Exception e) {}
        super.setMotorNames("motor_1", "motor_2", "motor_3", "motor_4");
        waitForStart();
        executeScript("Drive 0.5 0.5 1000"); //extra letters should be ignored by parse jk this isnt js
        executeScript("Drive -0.5 -0.5", "Time 1000");//Test both - This uses the endcon
    }
}
