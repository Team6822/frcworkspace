package org.usfirst.frc.team6822.robot.subsystems;

import org.usfirst.frc.team6822.robot.RobotMap;
import org.usfirst.frc.team6822.robot.commands.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Platform extends Subsystem {

	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DoubleSolenoid Lplat = RobotMap.Lplatform;
	public DoubleSolenoid Rplat = RobotMap.Rplatform;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    

    public void setState(String state){
        if(state == "Off"){
            Lplat.set(DoubleSolenoid.Value.kOff);
            Rplat.set(DoubleSolenoid.Value.kOff);
        }
        else if(state == "Forward"){
            Lplat.set(DoubleSolenoid.Value.kForward);
            Rplat.set(DoubleSolenoid.Value.kForward);
        }
        else if(state == "Reverse"){
            Lplat.set(DoubleSolenoid.Value.kReverse);
            Rplat.set(DoubleSolenoid.Value.kReverse);
        }
    }
}

