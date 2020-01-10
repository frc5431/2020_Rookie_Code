package frc.robot.components;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class AutonClass {
    private double startTime;
    private int section;
    private AHRS navx;
    private int times; 

    public AutonClass() {
        navx = new AHRS(SPI.Port.kMXP);
        reset();
        times = 0;
    }

    public void reset() {
        section = 0;
        setStartTime();
        navx.reset();
        navx.resetDisplacement();
    }

    public void resetNavx() {
        navx.reset();
        navx.resetDisplacement();
    }

    private double getSystemTime() {
        final double val = Timer.getFPGATimestamp();
        System.out.println(val);
        return val;
    }

    public void setStartTime() {
        startTime = getSystemTime();
    }


    public void runAuton(final Robot robot) {
        final Drivebase db = robot.getDrivebase();
        switch (section) {
            case 0:
                db.drive(0.5, 0.5);
                if (getSystemTime() > startTime + 1) {
                    section = 10;
                }
                break;
            case 10:
                db.drive(0, 0);
                resetNavx();
                section = 11;
                break;
            case 11:
                
                if (navx.getAngle() < 88) {
                    if (navx.getAngle() > 92) {
        
                        db.drive(-0.25, 0.25);
                    } else {
                        db.drive(0.35, -0.35);
                    }
            
                    section = 0;
                
                    reset();
                    times ++ ;
                    if (times > 3) {
                        section = 20; 
                    }
                    
                }
                break;
            default:
                db.drive(0, 0);
                break;
        }
    }
}