package com.qualcomm.ftcrobotcontroller.opmodes;

import java.io.IOException;

/**
 * Created by Iam on 11/15/2015.
 */
public class ScriptReaderAuton extends FourWheelDriveBaseAuton {
    private ScriptReader scriptReader;

    @Override
    public void runOpMode() throws InterruptedException {
        try{scriptReader = ScriptReader.buildScriptReader("");}
        catch (Exception e) {}
        executeScript("Drive 0.5 0.5 1000"); //extra letters should be ignored by parse jk this isnt js
        executeScript("Drive -0.5 -0.5", "Time 1000");//Test both - This uses the endcon


    }
}
