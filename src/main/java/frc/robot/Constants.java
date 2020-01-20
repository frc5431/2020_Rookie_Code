package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.util.MotionMagic;
import frc.robot.util.RobotType;

public final class Constants {
	public static final RobotType ROBOT_TYPE = RobotType.PRACTICE;

	// ================================================================================
	// Teleop Controller Data
	// ================================================================================

	/*
	 * Controller name does not need to be the enitre string but must contain a word
	 * from the HID name.
	 * 
	 * Capitalization does not matter. All strings shoud be translated to lowercase
	 * by the software. Setting the the name of the controller is for safty as you
	 * do not want a secondary controller accidentally controlling the robot when it
	 * may have a different layout.
	 */

	public static final int DRIVER_XBOX_ID = 0;
	public static final double DRIVER_XBOX_DEADZONE = 0.10;
	public static final String DRIVER_XBOX_NAME = "xbox 360";

	// ================================================================================
	// Motor ID`s and Reverse State
	// ================================================================================

	// Drivebase Related
	public static final int DRIVEBASE_FRONT_LEFT_ID = 1;
	public static final int DRIVEBASE_MIDDLE_LEFT_ID = 2;
	public static final int DRIVEBASE_BACK_LEFT_ID = 3;
	public static final boolean DRIVEBASE_LEFT_REVERSE = false;

	public static final int DRIVEBASE_FRONT_RIGHT_ID = 4;
	public static final int DRIVEBASE_MIDDLE_RIGHT_ID = 5;
	public static final int DRIVEBASE_BACK_RIGHT_ID = 6;
	public static final boolean DRIVEBASE_RIGHT_REVERSE = true;

	public static final NeutralMode DRIVEBASE_NEUTRAL_MODE = NeutralMode.Brake;

	// ================================================================================
	// Drive Base Motion Magic
	// ================================================================================

	public static final int DRIVEBASE_TIMEOUT_MS = 30;

	// TODO: Set Proper PID Values
	// P, I, D, F, INTERGRAL_ZONE, PEAKOUTPUT, CLOSEDLOOPTIME_MS
	public static final MotionMagic DRIVEBASE_MOTIONMAGIC_GAINS = new MotionMagic(0.2, 0, 0, 0, 100, 1, 1);
	// public static final MotionMagic DRIVEBASE_MOTIONMAGIC_DRIVE_GAINS = new
	// MotionMagic(0.2, 0, 0, 0, 100, 1, 1);
	// public static final MotionMagic DRIVEBASE_MOTIONMAGIC_TURN_GAINS = new
	// MotionMagic(0.2, 0, 0, 0, 100, 1, 1);
	// public static final int DRIVEBASE_MOTIONMAGIC_DRIVE_SLOT = SLOT_0;
	// public static final int DRIVEBASE_MOTIONMAGIC_TURN_SLOT = SLOT_1;

	public static final int DRIVEBASE_MOTIONMAGIC_SLOT = 0;
}