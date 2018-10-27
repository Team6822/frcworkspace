/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6822.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//can
	public static int talon1Intake = 1;
	//public static int talon2Intake = 2;
	//PWM
	public static int victorLinear = 7;
	//public static int victorClimber = 5;
	public static int sparkLeft = 9;
	public static int sparkRight = 8;
	//public static int intakeLeftSpot = 2;
	//public static int intakeRightSpot = 9;
	public static int leftClawSpot = 4;
	public static int rightClawSpot = 5;

	//Solenoids
	/*
	public static int LPlatformFwdPort = 0;
	public static int LPlatformBkwdPort = 1;
	public static int RPlatformFwdPort = 2;
	public static int RPlatformBkwdPort = 3;
	public static int LGripperFwdPort = 4;
	public static int LGripperBkwdPort = 5;
	public static int RGripperFwdPort = 6;
	public static int RGripperBkwdPort = 7;
	*/

	public static int OneGripperPortFwd = 0;
	public static int OneGripperPortBack = 1;
	
	//motors
	//public static TalonSRX talon2;
	public static VictorSP linear; // actually a victorsp
	public static VictorSP climber; // actually a victorsp
	public static VictorSP intakeright; // actually a victorspx connected with pwm
	public static VictorSP intakeleft;

	public static Spark leftDrive;
	public static Spark rightDrive;
	public static Spark leftClaw;
	public static Spark rightClaw;
	
	public static DifferentialDrive diffdrive;

	public static DoubleSolenoid LGripper;
	public static DoubleSolenoid RGripper;
	public static DoubleSolenoid Lplatform;
	public static DoubleSolenoid Rplatform;
	public static DoubleSolenoid OneGripper;

	public static void init()
	{
		//talon1 = new TalonSRX(talon1Intake);
		//talon2 = new TalonSRX(talon2Intake);
		linear = new VictorSP(victorLinear);
		//sonicSensor = new Ultrasonic(1,1);
		//sonicSensor.setAutomaticMode(true);
		//climber = new VictorSP(victorClimber);
		leftDrive = new Spark(sparkLeft);
		rightDrive = new Spark(sparkRight);
		leftClaw = new Spark(leftClawSpot);
		rightClaw = new Spark(rightClawSpot);
		diffdrive = new DifferentialDrive(leftDrive,rightDrive);
		//intakeleft = new VictorSP(intakeLeftSpot);
		//intakeright = new VictorSP(intakeRightSpot);
		
		diffdrive.setSafetyEnabled(false);
		linear.setSafetyEnabled(false);
		//intakeleft.setSafetyEnabled(false);
		//intakeright.setSafetyEnabled(false);

		OneGripper = new DoubleSolenoid(OneGripperPortFwd, OneGripperPortBack);
		/*
		Lplatform = new DoubleSolenoid(LPlatformFwdPort, LPlatformBkwdPort);
		Rplatform = new DoubleSolenoid(RPlatformFwdPort, RPlatformBkwdPort);
		LGripper = new DoubleSolenoid(LGripperFwdPort, LGripperBkwdPort);
		RGripper = new DoubleSolenoid(RGripperFwdPort, RGripperBkwdPort);
		*/

		//drop1 = new VictorSP(drop1spot);
		//drop2 = new VictorSP(drop2spot);
	}
	
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}