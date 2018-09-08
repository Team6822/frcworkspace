/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6822.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber)

	public Joystick xbox = new Joystick(0);
	public Joystick joystick = new Joystick(1);//also an xbox
	public Button intakein = new JoystickButton(joystick, 5);
	public Button intakeout = new JoystickButton(joystick,6);
	public Button boost = new JoystickButton(xbox,6);
	public Button unthrottleclaw = new JoystickButton(joystick,2);
	/*public Button slideup = new JoystickButton(joystick,6);
	public Button slidedown = new JoystickButton(joystick,4);*/
	
	//public int leftaxis = 1;
	//public int rightaxis = 5;
	public int lTriggerAxis = 2;
	public int rTriggerAxis = 3;
	public int lClawAxis = 4;
	public int rClawAxis = 0;
	public int turnAxis = 0;
	public int slideAxis = 3;
	public int otherSlideAxis = 2;
	
	public double throttleBoost = 1.0;
	public double throttleDrive = 0.45;
	public double throttleTurn = 0.5;
	public double throttleSlide = -0.4;//0.35;
	public double throttleIntakeIn = -0.5;
	public double throttleIntakeOut = 0.5;
	public double throttleIntakeClawOut = -0.9;
	public double throttleIntakeClawIn = -0.9;
	
	public double tensionSlide = 0.05; //helps maintain the tension in the slide
	
	
	
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}