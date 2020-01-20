package frc.robot.components;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.DriveType;
import frc.team5431.titan.core.robot.Component;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Dashboard extends Component<Robot> {

    private final SendableChooser<DriveType> driveType = new SendableChooser<>();

    public Dashboard() {

        /*
         * Add selection for which drive code to use as there are differences in
         * preference
         */
        driveType.setDefaultOption("Tank Drive", DriveType.TANK);
        driveType.addOption("Arcade Drive", DriveType.ARCADE);
        SmartDashboard.putData("Drive Type", driveType);
    }

    @Override
    public void init(Robot robot) {
    }

    @Override
    public void periodic(Robot robot) {
        // Setting Data from dashboard

        // Push data to dashboard
        putString("Mode", robot.getMode().toString());
        putString("Driver Swapped", robot.getTeleop().getSwappedDriverStatus() ? "Swapped Drive" : "Regular Drive");
        putNumber("Drivebase Heading", robot.getDrivebase().getHeading());
    }

    @Override
    public void disabled(Robot robot) {
    }

    @Override
    public void tick(Robot robot) {
    }

    public DriveType getSelectedDriveType() {
        return driveType.getSelected();
    }

    public void putString(String key, String value) {
        SmartDashboard.putString(key, value);
    }

    public void putNumber(String key, double value) {
        SmartDashboard.putNumber(key, value);
    }

    public String getString(String key, String defaultStr) {
        return SmartDashboard.getString(key, defaultStr);
    }

    public double getNumber(String key, double defaultNum) {
        return SmartDashboard.getNumber(key, defaultNum);
    }
}