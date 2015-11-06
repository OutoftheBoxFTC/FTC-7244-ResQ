package main.java.com.ftc7244.resq;

/**
 * Created by Iam on 10/5/2015.
 */
public class SimpleTeleop extends FourWheelDriveBase{
    @Override
    public void loop() {
        setPowerFromJoy();
    }

    @Override
    public void start() {}

    @Override
    public void stop() {super.stop();}

    @Override
    public void init() {
        setMotorNames("motor_1", "motor_2", "motor_3", "motor_4");
    }

    public SimpleTeleop(){}
}
