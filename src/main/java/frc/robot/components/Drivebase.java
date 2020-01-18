package frc.robot.components;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import java.util.List;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.controller.PIDController;

public class Drivebase {
	private final WPI_TalonSRX leftFront, leftMiddle, leftBack, rightFront, rightMiddle, rightBack;
	private List<WPI_TalonSRX> motors = List.of();

	// 0.004, 0.00005, 0 - 12 ft
	private PIDController drivePID = new PIDController(Constants.DRIVE_PID_P, Constants.DRIVE_PID_I, Constants.DRIVE_PID_D);
	private PIDController anglePID = new PIDController(Constants.ANGLE_PID_P, Constants.ANGLE_PID_I, Constants.ANGLE_PID_D);

	public Drivebase(){
		leftFront = new WPI_TalonSRX(Constants.LEFT_FRONT_ID);
		leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
		// leftFront.configPulseWidthPeriod_EdgesPerRot(256, 0);
		leftFront.setInverted(Constants.LEFT_FRONT_INVERTED);

		leftMiddle = new WPI_TalonSRX(Constants.LEFT_MIDDLE_ID);
		leftMiddle.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
		// leftMiddle.configPulseWidthPeriod_EdgesPerRot(256, 0);
		leftMiddle.setInverted(Constants.LEFT_MIDDLE_INVERTED);

		leftBack = new WPI_TalonSRX(Constants.LEFT_BACK_ID);
		leftBack.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
		// leftBack.configPulseWidthPeriod_EdgesPerRot(256, 0);
		leftBack.setInverted(Constants.LEFT_BACK_INVERTED);
		
		rightFront = new WPI_TalonSRX(Constants.RIGHT_FRONT_ID);
		rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
		// rightFront.configPulseWidthPeriod_EdgesPerRot(256, 0);
		rightFront.setInverted(Constants.RIGHT_FRONT_INVERTED);

		rightMiddle = new WPI_TalonSRX(Constants.RIGHT_MIDDLE_ID);
		rightMiddle.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
		//rightMiddle.configPulseWidthPeriod_EdgesPerRot(256, 0);
		rightMiddle.setInverted(Constants.RIGHT_MIDDLE_INVERTED);

		rightBack = new WPI_TalonSRX(Constants.RIGHT_BACK_ID);
		rightBack.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
		// rightBack.configPulseWidthPeriod_EdgesPerRot(256, 0);
		rightBack.setInverted(Constants.RIGHT_BACK_INVERTED);

		motors = List.of(leftFront, leftMiddle, leftBack, rightFront, rightMiddle, rightBack);
		// setSetpoint(4000);

		motors.forEach((m) -> m.setNeutralMode(NeutralMode.Brake));
	}

	public PIDController getPIDController() {
		return drivePID;
	}

	public PIDController getAnglePIDController(){
		return anglePID;
	}

	public void resetEncoders() {
		motors.forEach((m) -> m.setSelectedSensorPosition(0));
	}

	public int getEncoderLeft() {
		return (leftFront.getSelectedSensorPosition() + leftMiddle.getSelectedSensorPosition() + leftBack.getSelectedSensorPosition())/3;
	}

	public int getEncoderRight() {
		return (rightFront.getSelectedSensorPosition() + rightMiddle.getSelectedSensorPosition() + rightBack.getSelectedSensorPosition())/3;
	}

	public double getEncoderDistanceLeft() {
		return (getEncoderLeft()) / Constants.COUNTS_PER_REVOLUTION * Constants.WHEEL_CIRCUMFERENCE * Constants.GEAR_RATIO;
	}

	public double getEncoderDistanceRight() {
		return (getEncoderRight()) / Constants.COUNTS_PER_REVOLUTION * Constants.WHEEL_CIRCUMFERENCE * Constants.GEAR_RATIO;
	}

	public double getEncoderDistance() {
		return (getEncoderDistanceLeft() + getEncoderDistanceRight()) / 2;
	}

	public void driveLeft(final double val){
		leftFront.set(val);
		leftMiddle.set(val);
		leftBack.set(val);
	}

	public void driveRight(final double val){
		rightFront.set(val);
		rightMiddle.set(val);
		rightBack.set(val);
	}

	public void drive(final double left, final double right){
		driveLeft(left);
		driveRight(right);
	}
}