package frc.robot.components;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


public class AutonClass {
    private int section;
    private AHRS navx;
    private double target = 24*12;
    
    public AutonClass() {
        navx = new AHRS(SPI.Port.kMXP);
        reset();
    }

    public void reset() {
        section = 0;
        navx.reset();
        navx.resetDisplacement();
    }

    public void resetNavx() {
        navx.reset();
        navx.resetDisplacement();
    }

    public void periodic(final Robot robot) {
        final Drivebase db = robot.getDrivebase();

        switch (section) {
            case 0:
                db.drive(db.getPIDController().calculate(db.getEncoderDistanceLeft(), target), db.getPIDController().calculate(db.getEncoderDistanceRight(), target));
                if (db.getPIDController().atSetpoint()) {
                    section = 10;
                }
                break;
            default:
                db.drive(0,0);
                System.out.println("Done!");
                break;
        }
    }
}