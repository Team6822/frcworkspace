package org.usfirst.frc.team6822.robot.commands;

import org.usfirst.frc.team6822.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrainControl extends Command {

    public DriveTrainControl() {
        requires(Robot.m_drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.m_drivetrain.move(0, 0);
    		//System.out.println(0);  
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println(Robot.m_oi.joystick.getRawAxis(3));
    	double speed = Robot.m_oi.boost.get() ? Robot.m_oi.throttleBoost: Robot.m_oi.throttleDrive;
    	Robot.m_drivetrain.moveWithCurve((Robot.m_oi.xbox.getRawAxis(Robot.m_oi.rTriggerAxis)-Robot.m_oi.xbox.getRawAxis(Robot.m_oi.lTriggerAxis))*speed, Robot.m_oi.xbox.getRawAxis(Robot.m_oi.turnAxis)*Robot.m_oi.throttleTurn*Math.signum(0.1+Robot.m_oi.xbox.getRawAxis(Robot.m_oi.rTriggerAxis)-Robot.m_oi.xbox.getRawAxis(Robot.m_oi.lTriggerAxis)), true);
    	//Robot.m_drivetrain.move(Robot.m_oi.xbox.getRawAxis(Robot.m_oi.leftaxis)*Robot.m_oi.throttleDrive, Robot.m_oi.xbox.getRawAxis(Robot.m_oi.rightaxis)*Robot.m_oi.throttleDrive);
    	//System.out.println(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_drivetrain.move(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Robot.m_drivetrain.move(0, 0);
    }
}