/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6822.robot.subsystems;

import org.usfirst.frc.team6822.robot.RobotMap;
import org.usfirst.frc.team6822.robot.commands.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Pneumatics extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid dSolenoid = RobotMap.clawSolenoid;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setState(String state){
    if(state == "Off"){
      dSolenoid.set(DoubleSolenoid.Value.kOff);
    }
    else if(state == "Forward"){
      dSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    else if(state == "Reverse"){
      dSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
  }
}
