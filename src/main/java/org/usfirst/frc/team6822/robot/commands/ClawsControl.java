package org.usfirst.frc.team6822.robot.commands;

import org.usfirst.frc.team6822.robot.Robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawsControl extends Command {

	
    public ClawsControl() {
        requires(Robot.m_claws);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.m_claws.moveLeftClaw(0);
    		Robot.m_claws.moveRightClaw(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println(Robot.m_oi.joystick.getRawAxis(Robot.m_oi.lClawAxis)+" "+Robot.m_oi.joystick.getRawAxis(Robot.m_oi.rClawAxis));
        
        //Uncomment block when we need to use this code again
        /*
        Robot.m_claws.moveLeftClaw(-Robot.m_oi.joystick.getRawAxis(Robot.m_oi.lClawAxis)*(Robot.m_oi.unthrottleclaw.get()?0.9*Math.signum(Robot.m_oi.throttleIntakeClawOut) : Robot.m_oi.throttleIntakeClawOut));
    	Robot.m_claws.moveRightClaw(Robot.m_oi.joystick.getRawAxis(Robot.m_oi.rClawAxis)*(Robot.m_oi.unthrottleclaw.get()?0.9*Math.signum(Robot.m_oi.throttleIntakeClawOut) : Robot.m_oi.throttleIntakeClawOut));
        */
    }

    // Make this return true when this Command no longer needs to run execute() this section is going to be replaced with the new pneumatics code
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_claws.moveLeftClaw(0);
		Robot.m_claws.moveRightClaw(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.m_claws.moveLeftClaw(0);
		Robot.m_claws.moveRightClaw(0);
    }
}
