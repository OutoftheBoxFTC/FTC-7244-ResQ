package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Iam on 1/4/2016.
 */
public class ClimbScript extends FourWheelDriveBaseAuton{
    private ScriptReader scriptReader;
    @Override
    public void runOpMode() throws InterruptedException {
        setMotorNames("motor_1", "motor_2", "motor_3", "motor_4");
        initServos();
        scriptReader = new ScriptReader("climber.txt");
        String script = scriptReader.getNextScript();
        while(!script.equals("done")) {
            executeScript(script);
            script = scriptReader.getNextScript();
        }
    }
}
