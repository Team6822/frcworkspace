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

    protected void initialize() {
    		Robot.m_drivetrain.move(0, 0);
    }

    protected void execute() {
        double speed = Robot.m_oi.boost.get() ? Robot.m_oi.throttleBoost : Robot.m_oi.throttleDrive;
        
    	Robot.m_drivetrain.moveWithCurve(
            (Robot.m_oi.xbox.getRawAxis(Robot.m_oi.rTriggerAxis) - Robot.m_oi.xbox.getRawAxis(Robot.m_oi.lTriggerAxis)) * speed,
            Robot.m_oi.xbox.getRawAxis(Robot.m_oi.turnAxis)
                * Robot.m_oi.throttleTurn * Math.signum(0.1 + Robot.m_oi.xbox.getRawAxis(Robot.m_oi.rTriggerAxis) - Robot.m_oi.xbox.getRawAxis(Robot.m_oi.lTriggerAxis)), true);
        
        //Robot.m_drivetrain.move(Robot.m_oi.xbox.getRawAxis(Robot.m_oi.leftaxis)*Robot.m_oi.throttleDrive, Robot.m_oi.xbox.getRawAxis(Robot.m_oi.rightaxis)*Robot.m_oi.throttleDrive);
    }

    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    	Robot.m_drivetrain.move(0, 0);
    }

    protected void interrupted() {
    	//Robot.m_drivetrain.move(0, 0);
    }
}