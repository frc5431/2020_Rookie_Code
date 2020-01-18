package frc.robot;

public final class Constants {
	public final static int DRIVER_JOYSTICK_ID = 0;
	public final static double DRIVER_JOYSTICK_DEADZONE = 0.1;

	public final static int LEFT_FRONT_ID = 1;
	public final static boolean LEFT_FRONT_INVERTED = true;

	public final static int LEFT_MIDDLE_ID = 2;
	public final static boolean LEFT_MIDDLE_INVERTED = true;
	
	public final static int LEFT_BACK_ID = 3;
	public final static boolean LEFT_BACK_INVERTED = true;

	public final static int RIGHT_FRONT_ID = 4;
	public final static boolean RIGHT_FRONT_INVERTED = false;

	public final static int RIGHT_MIDDLE_ID = 5;
	public final static boolean RIGHT_MIDDLE_INVERTED = false;

	public final static int RIGHT_BACK_ID = 6;
	public final static boolean RIGHT_BACK_INVERTED = false;

	public final static double WHEEL_CIRCUMFERENCE = 6 * Math.PI;
	public final static double COUNTS_PER_REVOLUTION = 4096;
	public final static double GEAR_RATIO = 3;

	public final static double DRIVE_PID_P = 0.004;
	public final static double DRIVE_PID_I = 0.0000;
	public final static double DRIVE_PID_D = 0.0000;

	public final static double ANGLE_PID_P = 0.01;
	public final static double ANGLE_PID_I = 0.00;
	public final static double ANGLE_PID_D = 0.00;
}