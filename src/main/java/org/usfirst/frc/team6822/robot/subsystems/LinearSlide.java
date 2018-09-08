package org.usfirst.frc.team6822.robot.subsystems;

import org.usfirst.frc.team6822.robot.RobotMap;
import org.usfirst.frc.team6822.robot.commands.LinearSlideControl;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LinearSlide extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	VictorSP slide = RobotMap.linear;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new LinearSlideControl());
    }
    
    public void move(double speed)
    {
    		slide.set(speed);
    }
    
    
    
}
