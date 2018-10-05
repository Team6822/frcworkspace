/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6822.robot.subsystems;

import org.usfirst.frc.team6822.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Gripper extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

    public DoubleSolenoid LGripper = RobotMap.RGripper;
	public DoubleSolenoid RGripper = RobotMap.LGripper;

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void setState(String state){
        if(state == "Off"){
            LGripper.set(DoubleSolenoid.Value.kOff);
            RGripper.set(DoubleSolenoid.Value.kOff);
        }
        else if(state == "Close"){
            LGripper.set(DoubleSolenoid.Value.kForward);
            RGripper.set(DoubleSolenoid.Value.kForward);
        }
        else if(state == "Open"){
            LGripper.set(DoubleSolenoid.Value.kReverse);
            RGripper.set(DoubleSolenoid.Value.kReverse);
        }
    }
}
