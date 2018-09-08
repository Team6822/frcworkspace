package org.usfirst.frc.team6822.robot.subsystems;

import org.usfirst.frc.team6822.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claws extends Subsystem {

	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Spark leftClaw = RobotMap.leftClaw;
	public Spark rightClaw = RobotMap.rightClaw;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void moveLeftClaw(double speed)
    {
    	leftClaw.set(speed);
    }
    public void moveRightClaw(double speed)
    {
    	rightClaw.set(speed);
    }
}

