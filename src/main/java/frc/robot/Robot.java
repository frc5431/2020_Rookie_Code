package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.components.*;

public class Robot extends TimedRobot{
	public static enum Mode{
		DISABLED, TELEOP, AUTONOMOUS
	}

	private Mode mode = Mode.DISABLED;

	private Drivebase drivebase;

	private Teleop teleop;

	private AutonClass auton;

	@Override
	public void robotInit(){
		teleop = new Teleop();

		drivebase = new Drivebase();

		auton = new AutonClass();

		drivebase.resetEncoders();
	}

	@Override
	public void robotPeriodic(){
	}

	@Override
	public void autonomousInit() {
		mode = Mode.AUTONOMOUS;
		auton.reset();
		drivebase.resetEncoders();
	}

	@Override
	public void autonomousPeriodic() {
		auton.periodic(this);
	}

	@Override
	public void teleopInit(){
		mode = Mode.TELEOP;
	}

	@Override
	public void teleopPeriodic(){
		teleop.periodic(this);
	}

	@Override
	public void disabledInit(){
		mode = Mode.DISABLED;
	}

	public Mode getMode(){
		return mode;
	}

	public Teleop getTeleop(){
		return teleop;
	}
	
	public Drivebase getDrivebase(){
		return drivebase;
	}

	public AutonClass getAuton(){
		return auton;
	}
}