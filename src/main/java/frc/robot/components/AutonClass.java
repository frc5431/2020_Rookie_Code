package frc.robot.components;

import frc.robot.Robot;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


public class AutonClass {
	private int section;
	private AHRS navx;
	private double target = 12*12;
	private double angleTarget = 0; 
	
	public AutonClass() {
		navx = new AHRS(SPI.Port.kMXP);
		reset();
	}

	public void reset() {
		section = 0;
		resetNavx();
	}

	public void resetNavx() {
		navx.reset();
		navx.resetDisplacement();
		navx.zeroYaw();
	}

	public void periodic(final Robot robot) {
		final Drivebase db = robot.getDrivebase();

		System.out.println(navx.getYaw());
		// System.out.println("Left encoder: " + db.getEncoderLeft());
		// System.out.println("Right encoder: " + db.getEncoderRight());
		switch (section) {
			case 0:
				db.drive(
					(db.getPIDController().calculate(db.getEncoderDistanceLeft(), target)) + db.getAnglePIDController().calculate(navx.getYaw(), angleTarget),
					(db.getPIDController().calculate(db.getEncoderDistanceRight(), target)) - db.getAnglePIDController().calculate(navx.getYaw(), angleTarget) 
					);
				break;
			default:
				db.drive(0,0);
				System.out.println("Done!");
				break;
		}
	}
}