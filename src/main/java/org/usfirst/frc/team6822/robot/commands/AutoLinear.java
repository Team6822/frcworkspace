package org.usfirst.frc.team6822.robot.commands;

import org.usfirst.frc.team6822.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLinear extends Command {

	double move;
	long timeMillis;
	/**
	 * IMPORTANT. move is from -1 to 1, where 1 makes it go up and -1 makes it go down due to gravity, with all tension + thresholds accounted for.
	 * @param move
	 * @param fast
	 * @param timeMillis
	 */
    public AutoLinear(double move,long timeMillis)
    {
    	this.move = (move+1+Robot.m_oi.tensionSlide)*Robot.m_oi.throttleSlide;
    	this.timeMillis=timeMillis+System.currentTimeMillis();
        requires(Robot.m_linearslide);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(System.currentTimeMillis()<timeMillis)
    	{
    		Robot.m_linearslide.move(move);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis()>=timeMillis;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_linearslide.move(Robot.m_oi.throttleSlide);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
