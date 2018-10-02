/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6822.robot.commands;

import org.usfirst.frc.team6822.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class GripperControl extends Command {
    public GripperControl() {
        requires(Robot.m_gripper);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.m_gripper.setState("Off");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if(Robot.m_oi.gripperButton.get()){
            Robot.m_gripper.setState("Forward");
        }
        else {
            Robot.m_gripper.setState("Reverse");
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.m_gripper.setState("Off");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	Robot.m_gripper.setState("Off");
    }
}
