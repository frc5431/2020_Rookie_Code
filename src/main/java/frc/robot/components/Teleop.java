package frc.robot.components;

import frc.robot.components.Drivebase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.Titan;

public class Teleop{
    private Titan.Xbox driver;

    public Teleop(){
        driver = new Titan.Xbox(Constants.DRIVER_JOYSTICK_ID);
        driver.setDeadzone(Constants.DRIVER_JOYSTICK_DEADZONE);
    }

    public void periodic(final Robot robot){
        //Tank Drive
        robot.getDrivebase().drive(-driver.getRawAxis(Titan.Xbox.Axis.LEFT_Y)*.75, -driver.getRawAxis(Titan.Xbox.Axis.RIGHT_Y)*.75);
        
        //Arcade Drive
        //robot.getDrivebase().drive(-driver.getRawAxis(Titan.Xbox.Axis.LEFT_X)*.4 + driver.getRawAxis(Titan.Xbox.Axis.LEFT_Y), driver.getRawAxis(Titan.Xbox.Axis.LEFT_X)*.4+ driver.getRawAxis(Titan.Xbox.Axis.LEFT_Y));
    }

    public Titan.Xbox getDriver(){
        return driver;
    }
}
