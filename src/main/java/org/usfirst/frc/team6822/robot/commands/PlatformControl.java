package org.usfirst.frc.team6822.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6822.robot.Robot;

/**
 *
 */
public class PlatformControl extends Command {

	
    public PlatformControl() {
        requires(Robot.m_platform);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_platform.setState("Off");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Robot.m_oi.platformForward.get()){
            Robot.m_platform.setState("Forward");
        }
        else if(Robot.m_oi.platformBack.get()){
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
