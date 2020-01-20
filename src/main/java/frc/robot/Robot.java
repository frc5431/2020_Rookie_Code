package frc.robot;

import java.util.List;

import frc.robot.components.*;
import frc.team5431.titan.core.misc.Logger;
import frc.team5431.titan.core.robot.Component;
import frc.team5431.titan.core.robot.TitanRobot;

public class Robot extends TitanRobot<Robot> {
	public static enum Mode {
		DISABLED, AUTO, TELEOP, TEST
	}

	// Component Objects
	private Dashboard dashboard;
	private Drivebase drivebase;
	private Teleop teleop;
	private Autonomous auton;

	// Objects for mostly internal Robot.java usage
	private Mode mode = Mode.DISABLED;
	private List<Component<Robot>> components = List.of();

	// The Following is Initializer Functions

	@Override
	public void robotInit() {
		Logger.DEBUG = true;

		// Initialize Components
		// colorWheel = new ControlPanel();
		dashboard = new Dashboard();
		drivebase = new Drivebase();
		teleop = new Teleop();
		auton = new Autonomous();

		// Add Components to components Array
		components = List.of(/* colorWheel, */ dashboard, drivebase, teleop);
	}

	@Override
	public void teleopInit() {
		mode = Mode.TELEOP;
		components.forEach((com) -> com.init(this));
	}

	@Override
	public void autonomousInit() {
		mode = Mode.AUTO;
		components.forEach((com) -> com.init(this));
	}

	@Override
	public void testInit() {
		mode = Mode.TEST;
		components.forEach((com) -> com.init(this));
	}

	@Override
	public void disabledInit() {
		mode = Mode.DISABLED;
		components.forEach((com) -> com.disabled(this));
	}

	// The Following is Periodic Functions

	@Override
	public void robotPeriodic() {
		components.forEach((com) -> com.tick(this));
	}

	@Override
	public void teleopPeriodic() {
		components.forEach((com) -> com.periodic(this));
	}

	@Override
	public void autonomousPeriodic() {
		teleopPeriodic();
	}

	@Override
	public void testPeriodic() {
		teleopPeriodic();
	}

	@Override
	public void disabledPeriodic() {
		// This Function Should Do Nothing
	}

	/**
	 * @return the components
	 */
	@Override
	public List<Component<Robot>> getComponents() {
		return components;
	}

	/**
	 * @return the mode
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * @return the drivebase
	 */
	public Drivebase getDrivebase() {
		return drivebase;
	}

	/**
	 * @return the dashboard
	 */
	public Dashboard getDashboard() {
		return dashboard;
	}

	/**
	 * @return the teleop
	 */
	public Teleop getTeleop() {
		return teleop;
	}

	/**
	 * @return the autonomous
	 */
	public Autonomous getAutonomous() {
		return auton;
	}
}
