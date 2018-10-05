package org.usfirst.frc.team6822.robot.commands;

import org.usfirst.frc.team6822.robot.Robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeControl extends Command {

	
    public IntakeControl() {
        requires(Robot.m_intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_intake.moveMotors(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //uncomment block when need to use this code again
        /*
    	if(Robot.m_oi.intakeout.get())
		{
    		Robot.m_intake.moveMotors(Robot.m_oi.throttleIntakeOut);
    	}
    	else if(Robot.m_oi.intakein.get())
    	{
    		Robot.m_intake.moveMotors(Robot.m_oi.throttleIntakeIn);
    	}
    	else
    	{
    		Robot.m_intake.moveMotors(0);
        }
        */
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_intake.moveMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.m_intake.moveMotors(0);
    }
}
