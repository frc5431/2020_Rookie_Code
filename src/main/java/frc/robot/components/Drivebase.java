package frc.robot.components;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Drivebase {
    private final WPI_TalonSRX leftFront, leftMiddle, leftBack, rightFront, rightMiddle, rightBack;

    public Drivebase(){
        leftFront = new WPI_TalonSRX(Constants.LEFT_FRONT_ID);
        leftFront.setInverted(Constants.LEFT_FRONT_INVERTED);

        leftMiddle = new WPI_TalonSRX(Constants.LEFT_MIDDLE_ID);
        leftMiddle.setInverted(Constants.LEFT_MIDDLE_INVERTED);

        leftBack = new WPI_TalonSRX(Constants.LEFT_BACK_ID);
        leftBack.setInverted(Constants.LEFT_BACK_INVERTED);
        
        rightFront = new WPI_TalonSRX(Constants.RIGHT_FRONT_ID);
        rightFront.setInverted(Constants.RIGHT_FRONT_INVERTED);

        rightMiddle = new WPI_TalonSRX(Constants.RIGHT_MIDDLE_ID);
        rightMiddle.setInverted(Constants.RIGHT_MIDDLE_INVERTED);

        rightBack = new WPI_TalonSRX(Constants.RIGHT_BACK_ID);
        rightBack.setInverted(Constants.RIGHT_BACK_INVERTED);
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