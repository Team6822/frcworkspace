/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*
This is the Robot.java after October 1 commit "actual merge commit (forgot to save merged files)", but with commented code
removed for clarity.
Don't delete the current (old) robot.java though, commented code might be useful later. Also some of the comment code might
need to be uncommented right now.
*/

package org.usfirst.frc.team6822.robot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.usfirst.frc.team6822.robot.commands.*;
import org.usfirst.frc.team6822.robot.subsystems.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

public class NewRobot extends TimedRobot 
{
    public static OI m_oi;
	public static Intake m_intake;
	public static LinearSlide m_linearslide;
	public static DriveTrain m_drivetrain;
	public static Claws m_claws;
    public static Platform m_platform;
    public static Gripper m_gripper;
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
        RobotMap.init();
        
		UsbCamera theCamera = CameraServer.getInstance().startAutomaticCapture();
		theCamera.setVideoMode(theCamera.enumerateVideoModes()[101]);

		m_oi = new OI();
		m_intake = new Intake();
		m_linearslide = new LinearSlide();
		m_drivetrain = new DriveTrain();
        m_claws = new Claws();
        m_platform = new Platform();
        m_gripper = new Gripper();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	
	double[][] loadedAction = new double[1000][8];
	@Override
	public void autonomousInit() {
		boolean isSimple = true;
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(isSimple)
		{
			CommandGroup routine = new CommandGroup();
			routine.addSequential(new AutoDriveControl(0.5,0.00,true,2000));
			
			routine.start();
			if(isSimple)
			{
				return;
			}
		}
		else
		{
			return;
		}
		
		int LOCATION = DriverStation.getInstance().getLocation();
        //location 1=left 2=center 3=right
        
		if(!SmartDashboard.containsKey("Length1"))
		{
			SmartDashboard.putNumber("Length1", 1000);
		}
		if(!SmartDashboard.containsKey("Length2"))
		{
			SmartDashboard.putNumber("Length2", 500);
		}
		if(!SmartDashboard.containsKey("Angle"))
		{
			SmartDashboard.putNumber("Angle", 0.05);
		}
		
		
		int position = LOCATION-1;
		boolean isOnRight = gameData.charAt(0)=='R';
		if(isOnRight)
		{
			position+=3;
		}
		boolean isStupid = false;
        //possibly delete the "stupid plan" (record autonomous) section from NewRobot.java (this file) if it is not being used
        //again, don't delete anything from Robot.java
        if(isStupid)//stupid plan
		{
			/*
			Record commands beforehand and run them
			*/
			try {
				BufferedReader bR = new BufferedReader(new FileReader("/home/lvuser/recorded."+position));
				String readingTemp;
				int i = 0;
				while((readingTemp = bR.readLine())!=null)
				{
					String[] split = readingTemp.split(",");
					for(int j = 0;j<8;j++)
					{
						loadedAction[i][j]=Double.parseDouble(split[j]);
					}
					i++;
					System.out.println(loadedAction[i][0]);
				}
				bR.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			CommandGroup routine = new CommandGroup();
			if(gameData.length() > 0)
			{
				
				if(LOCATION==1)//LEFT
				{
					if(gameData.charAt(0) == 'L')
					{
						
						routine.addParallel(new AutoLinear(0.5,1000));
						routine.addSequential(new AutoDriveControl(0.4,SmartDashboard.getNumber("Angle", 0.05),true,(long) SmartDashboard.getNumber("Length1", 1000)));
						routine.addSequential(new AutoDriveControl(0.4,-SmartDashboard.getNumber("Angle", 0.05),true,(long) SmartDashboard.getNumber("Length2", 500)));
	
						routine.addSequential(new AutoIntake(false,500));
						routine.start();
						
					}
					else 
					{
						//CommandGroup routine = new CommandGroup();
						routine.addParallel(new AutoLinear(0.8,2000));
						routine.addSequential(new AutoDriveControl(0.5,-0.15,true,1500));
						routine.addSequential(new AutoDriveControl(0.5,0.1,true,2000));
						routine.start();
					}
				}
				else if(LOCATION==2) //central
				{
					if(gameData.charAt(0) == 'L')
					{
						routine.addParallel(new AutoLinear(0.5,1000));
						routine.addSequential(new AutoDriveControl(0.4,0,true,500));
						routine.addSequential(new AutoDriveControl(0.4,-0.5,true,(long) SmartDashboard.getNumber("Length1", 500)));
						routine.addSequential(new AutoDriveControl(0.5,0.1,true,(long) SmartDashboard.getNumber("Length2", 500)));
	
						routine.addSequential(new AutoIntake(false,1000));
						routine.start();
						
					}
					else 
					{
						routine.addSequential(new AutoDriveControl(0.4,0.03,true,1000));
						routine.addSequential(new AutoDriveControl(0.4,-0.03,true,1000));
						routine.addSequential(new AutoIntake(false,1000));
						routine.start();
					}
				}
				else if(LOCATION==3) //right
				{
					if(gameData.charAt(0) == 'L')
					{
						//CommandGroup routine = new CommandGroup();
						routine.addParallel(new AutoLinear(0.8,2000));
						routine.addSequential(new AutoDriveControl(0.5,0.15,true,1500));
						routine.addSequential(new AutoDriveControl(0.5,-0.1,true,2000));
						routine.start();
						
					}
					else 
					{
						//CommandGroup routine = new CommandGroup();
						routine.addParallel(new AutoLinear(0.5,1000));
						routine.addSequential(new AutoDriveControl(0.4,-SmartDashboard.getNumber("Angle", 0.05),true,(long) SmartDashboard.getNumber("Length1", 1000)));
						routine.addSequential(new AutoDriveControl(0.4,SmartDashboard.getNumber("Angle", 0.05),true,(long) SmartDashboard.getNumber("Length2", 500)));
	
						routine.addSequential(new AutoIntake(false,500));
						routine.start();
					}
				}
			}
			else
			{
				System.out.println("PLEASE SPECIFY GAMEDATA");
			}
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	
	int positionAtRecording = 0;
	
	@Override
	public void autonomousPeriodic() 
	{
        Scheduler.getInstance().run();

        boolean tryInterestingIdea = false;
        if(!tryInterestingIdea)
        {
			return;
        }
        else
        {
            //interesting idea letsgo
            try
            {
                double[] tempReading = loadedAction[positionAtRecording];
                positionAtRecording++;
                
                
                if(tempReading[7]>=0.5)
                {
                    Robot.m_intake.moveMotors(Robot.m_oi.throttleIntakeOut);
                }
                else if(tempReading[6]>=0.5)
                {
                    Robot.m_intake.moveMotors(Robot.m_oi.throttleIntakeIn);
                }
                else
                {
                    Robot.m_intake.moveMotors(0);
                }
                
                
                //FIX THIS
                /*Robot.m_claws.moveLeftClaw(tempReading[4]*Robot.m_oi.throttleIntakeClaw);
                Robot.m_claws.moveRightClaw(tempReading[5]*Robot.m_oi.throttleIntakeClaw);*/
        
                
                Robot.m_drivetrain.moveWithCurve(tempReading[1]*Robot.m_oi.throttleDrive, tempReading[2]*Robot.m_oi.throttleTurn, true);
                Robot.m_linearslide.move(tempReading[3]);
            }
            catch(Exception err)
            {
                System.out.println(err.toString());
            }
        }
	}
	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		new TeleOpCommands().start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	
	long timeAtZero = 0;
	boolean isRecording = false;
	boolean hasRecorded = false;
	PrintWriter pW ;
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		if(Robot.m_oi.joystick.getRawButton(3) && Robot.m_oi.joystick.getRawButton(4))
		{
			if(!isRecording && !hasRecorded)
			{
			timeAtZero = System.currentTimeMillis();
			isRecording = true;
			
				try {
					pW = new PrintWriter(new FileWriter("/home/lvuser/recordedy.0"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(isRecording && (System.currentTimeMillis() - timeAtZero > 1000))
			{
				isRecording = false;
				hasRecorded = true;
				pW.flush();
				pW.close();
			}
		}
		if(isRecording)
		{
			pW.println(
                (System.currentTimeMillis() - timeAtZero) + ","
		        + (Robot.m_oi.xbox.getRawAxis(Robot.m_oi.rTriggerAxis) - Robot.m_oi.xbox.getRawAxis(Robot.m_oi.lTriggerAxis)) + ","
		        + Robot.m_oi.xbox.getRawAxis(Robot.m_oi.turnAxis) + ","
                + (
                    (Robot.m_oi.joystick.getRawAxis(Robot.m_oi.slideAxis)
                    + Robot.m_oi.tensionSlide
                    - 0.2 * Robot.m_oi.joystick.getRawAxis(Robot.m_oi.otherSlideAxis)
                ) * Robot.m_oi.throttleSlide) + ","
                + Robot.m_oi.joystick.getRawAxis(Robot.m_oi.lClawAxis) + ","
                + Robot.m_oi.joystick.getRawAxis(Robot.m_oi.rClawAxis) + ","
                + (Robot.m_oi.intakein.get() ? 1.0 : 0.0) + ","
                + (Robot.m_oi.intakeout.get() ? 1.0 : 0.0)
            );

			System.out.println((System.currentTimeMillis() - timeAtZero) + " " + 1000 * 15);
		}
	}

	@Override
	public void testInit(){
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}