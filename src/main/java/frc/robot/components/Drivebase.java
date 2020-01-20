package frc.robot.components;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.MotionMagic;
import frc.team5431.titan.core.misc.Toggle;
import frc.team5431.titan.core.robot.Component;

public class Drivebase extends Component<Robot> {

	private AHRS navx;

    private WPI_TalonSRX left;
    private WPI_TalonSRX right;

	private WPI_TalonSRX _leftMiddle;
	private WPI_TalonSRX _rightMiddle;

    private WPI_TalonSRX _leftFollow;
    private WPI_TalonSRX _rightFollow;

    private Toggle swappedDrive;

    public Drivebase() {

		navx = new AHRS(SPI.Port.kMXP);

        left = new WPI_TalonSRX(Constants.DRIVEBASE_FRONT_LEFT_ID);
        right = new WPI_TalonSRX(Constants.DRIVEBASE_FRONT_RIGHT_ID);

		_leftMiddle = new WPI_TalonSRX(Constants.DRIVEBASE_MIDDLE_LEFT_ID);
		_rightMiddle = new WPI_TalonSRX(Constants.DRIVEBASE_MIDDLE_RIGHT_ID);

        _leftFollow = new WPI_TalonSRX(Constants.DRIVEBASE_BACK_LEFT_ID);
        _rightFollow = new WPI_TalonSRX(Constants.DRIVEBASE_BACK_RIGHT_ID);

        left.setInverted(Constants.DRIVEBASE_LEFT_REVERSE);
		right.setInverted(Constants.DRIVEBASE_RIGHT_REVERSE);
		_leftMiddle.setInverted(Constants.DRIVEBASE_LEFT_REVERSE);
		_rightMiddle.setInverted(Constants.DRIVEBASE_RIGHT_REVERSE);
        _leftFollow.setInverted(Constants.DRIVEBASE_LEFT_REVERSE);
        _rightFollow.setInverted(Constants.DRIVEBASE_RIGHT_REVERSE);

		_leftMiddle.follow(left);
		_rightMiddle.follow(right);
        _leftFollow.follow(left);
        _rightFollow.follow(right);

        /* Factory Default all hardware to prevent unexpected behavior */
        left.configFactoryDefault();
		right.configFactoryDefault();
		

        /* Set what state the motors will be at when the speed is at zero */
        left.setNeutralMode(Constants.DRIVEBASE_NEUTRAL_MODE);
        right.setNeutralMode(Constants.DRIVEBASE_NEUTRAL_MODE);

        /* Set the motor output ranges */
        left.configPeakOutputForward(1, Constants.DRIVEBASE_TIMEOUT_MS);
        left.configPeakOutputReverse(-1, Constants.DRIVEBASE_TIMEOUT_MS);
        right.configPeakOutputForward(1, Constants.DRIVEBASE_TIMEOUT_MS);
        right.configPeakOutputReverse(-1, Constants.DRIVEBASE_TIMEOUT_MS);

        /* Tell motors which sensors it is reading from */
        left.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Constants.DRIVEBASE_TIMEOUT_MS);
        right.configSelectedFeedbackSensor(FeedbackDevice.SensorSum, 0, Constants.DRIVEBASE_TIMEOUT_MS);

        right.configSelectedFeedbackCoefficient(0.5, 0, Constants.DRIVEBASE_TIMEOUT_MS);

        /* Set PID values for each slot */
        setPID(Constants.DRIVEBASE_MOTIONMAGIC_SLOT, Constants.DRIVEBASE_MOTIONMAGIC_GAINS);
        // setPID(Constants.DRIVEBASE_MOTIONMAGIC_TURN_SLOT, Constants.DRIVEBASE_MOTIONMAGIC_TURN_GAINS);

        zeroGyro();
        zeroDistance();

        swappedDrive = new Toggle();
        swappedDrive.setState(false);
	}

    private void setPID(final int slot, final MotionMagic gain) {
        ErrorCode eCode = ErrorCode.OK;

        eCode = right.config_kP(slot, gain.kP);
        assert (eCode == ErrorCode.OK);

        eCode = right.config_kI(slot, gain.kI);
        assert (eCode == ErrorCode.OK);

        eCode = right.config_kD(slot, gain.kD);
        assert (eCode == ErrorCode.OK);

        eCode = right.config_kF(slot, gain.kF);
        assert (eCode == ErrorCode.OK);

        eCode = right.config_IntegralZone(slot, gain.kIntegralZone, Constants.DRIVEBASE_TIMEOUT_MS);
        assert (eCode == ErrorCode.OK);

        eCode = right.configClosedLoopPeakOutput(slot, gain.kPeakOutput, Constants.DRIVEBASE_TIMEOUT_MS);
        assert (eCode == ErrorCode.OK);

        eCode = right.configClosedLoopPeriod(slot, gain.kClosedLoopTime, Constants.DRIVEBASE_TIMEOUT_MS);
        assert (eCode == ErrorCode.OK);
    }

    private void zeroDistance() {
        ErrorCode eCode = ErrorCode.OK;
        eCode = left.getSensorCollection().setQuadraturePosition(0, Constants.DRIVEBASE_TIMEOUT_MS);
        assert (eCode == ErrorCode.OK);

        eCode = right.getSensorCollection().setQuadraturePosition(0, Constants.DRIVEBASE_TIMEOUT_MS);
        assert (eCode == ErrorCode.OK);
    }

    public void zeroGyro() {
        zeroDistance();

        navx.zeroYaw();
    }

    @Override
    public void init(Robot robot) {
    }

    @Override
    public void periodic(Robot robot) {
        // Check if the the motors are working together
        assert (left.get() == _leftFollow.get());
        assert (right.get() == _rightFollow.get());
    }

    @Override
    public void disabled(Robot robot) {
    }

    public void setSlot(int slot) {
        // Add asserts as the motorcontrollers only support 4 slots
        assert (slot >= 0);
        assert (slot <= 3);

        right.selectProfileSlot(slot, 0);
    }

    public void drivePercentageTank(double driveLeft, double driveRight) {
        left.set(ControlMode.PercentOutput, driveLeft);
        right.set(ControlMode.PercentOutput, driveRight);
    }

    public void drivePercentageArcade(double power, double turn) {
        /*
         * Arbitrary based turning. Theoretically better as it is controlled by the
         * speed controller.
         */

        left.set(ControlMode.PercentOutput, power, DemandType.ArbitraryFeedForward, -turn);
        right.set(ControlMode.PercentOutput, power, DemandType.ArbitraryFeedForward, +turn);
    }

    // public void driveMotionMagic(MotionMagicCommands command, double target, double optionalSensor) {
    public void driveMotionMagic(double distance, double angle) {
        // case FOWARD:
        // changeRemoteSensor(command);
        // setSlot(Constants.DRIVEBASE_MOTIONMAGIC_DRIVE_SLOT);

        // left.follow(right);
        // right.set(ControlMode.MotionMagic, target);
        // break;
        // case TURN:
        // changeRemoteSensor(command);
        // double targetSensor = optionalSensor * 4096 * 6;
        // double targetTurn = target * 4096 * 6;

        // setSlot(Constants.DRIVEBASE_MOTIONMAGIC_TURN_SLOT);
        // right.set(ControlMode.MotionMagic, targetSensor, DemandType.AuxPID,
        // targetTurn);
        // left.follow(right);
        // break;
        // default:
        // right.set(0);
        // left.set(0);
        // break;
        // }

        left.follow(right);
        right.set(ControlMode.MotionMagic, distance, DemandType.AuxPID, angle);
    }

    public double getHeading() {
        return navx.getYaw();
    }
}