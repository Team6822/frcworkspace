package org.usfirst.frc.team6822.robot.commands;

import org.usfirst.frc.team6822.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoIntake extends Command {

	double move;
	long timeMillis;
	/**
	 * @param move
	 * @param fast
	 * @param timeMillis
	 */
    public AutoIntake(boolean isIntaking, long timeMillis)
    {
    	this.move = isIntaking ? Robot.m_oi.throttleIntakeOut : Robot.m_oi.throttleIntakeIn;
    	
    	this.timeMillis=timeMillis+System.currentTimeMillis();
        requires(Robot.m_intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(System.currentTimeMillis()<timeMillis)
    	{
    		Robot.m_intake.moveMotors(move);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis()>=timeMillis;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_intake.moveMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
