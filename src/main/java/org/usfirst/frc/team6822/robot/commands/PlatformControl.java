package org.usfirst.frc.team6822.robot.commands;

import org.usfirst.frc.team6822.robot.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */
public class PlatformControl extends Command {

	
    public PLatformControl() {
        requires(Robot.m_platform);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.m_platform.setState("Off");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Robot.m_oi.platbutt.get())
        {
            Robot.m_platform.setState("Forward");
        }else {
            Robot.m_platform.setState("Reverse");
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_platform.setState("Off");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.m_platform.setState("Off");
    }
}
