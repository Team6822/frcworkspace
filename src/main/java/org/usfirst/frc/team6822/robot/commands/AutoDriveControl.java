package org.usfirst.frc.team6822.robot.commands;

import org.usfirst.frc.team6822.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveControl extends Command {

	double move;
	double curve;
	boolean fast;
	long timeMillis;
    public AutoDriveControl(double move, double curve, boolean fast, long timeMillis)
    {
    	this.move = move;
    	this.curve = curve;
    	this.fast=fast;
    	this.timeMillis=timeMillis+System.currentTimeMillis();
        requires(Robot.m_drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(System.currentTimeMillis()<timeMillis)
    	{
    		Robot.m_drivetrain.moveWithCurve(move, curve, fast);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis()>=timeMillis;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_drivetrain.moveWithCurve(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
