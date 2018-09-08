package org.usfirst.frc.team6822.robot.subsystems;

import org.usfirst.frc.team6822.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public VictorSP intakeLeft = RobotMap.intakeleft;
	public VictorSP intakeRight = RobotMap.intakeright;

	//public TalonSRX talon2 = RobotMap.talon2;
    public void initDefaultCommand() {
        //setDefaultCommand(new IntakeControl());
    }

    
    public void moveMotors(double speed)
    {
    		intakeLeft.set(-speed);
    		intakeRight.set(-speed);
    }  
}