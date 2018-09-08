package org.usfirst.frc.team6822.robot.commands;

import org.usfirst.frc.team6822.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LinearSlideControl extends Command {

	double maxSpeed = 1;
    public LinearSlideControl() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_linearslide);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.m_linearslide.move(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//\System.out.println(Robot.m_oi.joystick.getRawAxis(Robot.m_oi.slideAxis)+" "+((1-Robot.m_oi.joystick.getRawAxis(Robot.m_oi.slideAxis))*Robot.m_oi.throttleSlide));
	    	Robot.m_linearslide.move((Robot.m_oi.joystick.getRawAxis(Robot.m_oi.slideAxis)+Robot.m_oi.tensionSlide-Robot.m_oi.joystick.getRawAxis(Robot.m_oi.otherSlideAxis)*0.5)*Robot.m_oi.throttleSlide);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_linearslide.move(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.m_linearslide.move(0);
    }
}
