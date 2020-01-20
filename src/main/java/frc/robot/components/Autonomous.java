package frc.robot.components;

import frc.robot.Robot;
import frc.robot.Robot.Mode;
import frc.team5431.titan.core.robot.Component;

public class Autonomous extends Component<Robot> {
    int section = 0;

    public Autonomous() {

    }

    @Override
    public void init(Robot robot) {
        robot.getDrivebase().zeroGyro();
    }

    @Override
    public void periodic(Robot robot) {
        // Do not run if not in Autonomous mode
        if (robot.getMode() != Mode.AUTO)
            return;

        
    }

    @Override
    public void disabled(Robot robot) {
    }

}