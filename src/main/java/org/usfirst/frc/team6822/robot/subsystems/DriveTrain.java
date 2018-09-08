package org.usfirst.frc.team6822.robot.subsystems;

import org.usfirst.frc.team6822.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public DriveTrain()
	{
		//super("DriveTrain",0.2,0,0);
		//setAbsoluteTolerance(0.05);
	}
	DifferentialDrive drivy = RobotMap.diffdrive;
    public void initDefaultCommand() {
       //setDefaultCommand(new DriveTrainControl());
    }
    
    /**
     * regular move
     * @param left
     * @param right
     */
    public void move(double left, double right)
    {
    		drivy.tankDrive(left, right);
    }
    
    /**
     * Moves with angle
     * @param forward
     * @param angle
     * @param DO_YOU_WANT_IT_FAST
     */
    public void moveWithCurve(double forward, double angle,boolean DO_YOU_WANT_IT_FAST)
    {
    		drivy.curvatureDrive(forward, angle, DO_YOU_WANT_IT_FAST);
    }

	/*@Override
	protected double returnPIDInput() {
		return RobotMap.sonicSensor.getRangeInches();
	}

	@Override
	protected void usePIDOutput(double output) {
		drivy.tankDrive(-output, -output);
	}   */
}
