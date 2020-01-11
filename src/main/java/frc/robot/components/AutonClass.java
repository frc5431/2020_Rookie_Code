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
    private int previousSection;

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
        // System.out.println(val);
        return val;
    }

    public void setStartTime() {
        startTime = getSystemTime();
    }


    public void runAuton(final Robot robot) {
        final Drivebase db = robot.getDrivebase();
        switch (section) {
            case 0:
                db.drive(0.4, 0.4);
                if (getSystemTime() > startTime + 1.7) {
                    section = 10;
                
                }
                previousSection = section;
                break;
            case 10:
                db.drive(0, 0);
                resetNavx();
                if (times == 0){
                    section = 13;
                }
                else if (times == 1){
                    section = 15;
                }
                else if (times == 2){
                    section = 11;
                    
                }
                else if (times == 3){
                    section = 14;
                    
                }
                else {
                    section = 20;
                
                }
                break;
            case 11:
                if (navx.getAngle() < 89) {
                    db.drive(0.3, -0.3);
                    section = 12;
                    setStartTime();
                } else if (navx.getAngle() > 91) {
                    db.drive(-0.275, 0.275);
                    section = 12;
                    setStartTime();
                } else {
                    section = 0;
                    reset();
                    times++;
                }
                System.out.println(navx.getAngle());
                previousSection = section;
                break;
            case 12:   
                if (getSystemTime() > startTime + 5){
                    section = previousSection;
                }
            case 15:
                if (navx.getAngle() > -44) {
                    db.drive(-0.3, 0.3);
                    section = 12;
                    setStartTime();
                } else if (navx.getAngle() < -46) {
                    db.drive(.275, -0.275);
                    section = 12;
                    setStartTime();
                } else {
                    section = 0;
                    reset();
                    times++;
                }
                System.out.println(navx.getAngle());
                previousSection = section;
                 break;
            case 13:
                if (navx.getAngle() > -69) {
                    db.drive(-.3, 0.3);
                    section = 12;
                    setStartTime();
                } else if (navx.getAngle() < -71) {
                    db.drive(0.275, -0.275);
                    section = 12;
                    setStartTime();
                } else {
                    section = 0;
                    reset();
                    times++;                  
                }
                System.out.println(navx.getAngle());
                previousSection = section;
                break;
            case 14:
                if (navx.getAngle() < 44) {
                    db.drive(0.3, -0.3);
                    section = 12;
                    setStartTime();
                } else if (navx.getAngle() > 46) {
                    db.drive(-.275, 0.275);
                    section = 12;
                    setStartTime();
                } else {
                    section = 0;
                    reset();
                    times ++;
                    
                }
                System.out.println(navx.getAngle());
                previousSection = section;
                 break;
            default:
                db.drive(0, 0);
                break;
        }
        System.out.println(section);
    }
}