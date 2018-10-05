/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6822.robot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// import org.usfirst.frc.team6822.robot.commands.AutoDriveControl;
// import org.usfirst.frc.team6822.robot.commands.AutoIntake;
// import org.usfirst.frc.team6822.robot.commands.AutoLinear;
// import org.usfirst.frc.team6822.robot.commands.LinearSlideControl;
// import org.usfirst.frc.team6822.robot.commands.TeleOpCommands;
// import org.usfirst.frc.team6822.robot.subsystems.Claws;
// import org.usfirst.frc.team6822.robot.subsystems.DriveTrain;
// import org.usfirst.frc.team6822.robot.subsystems.Intake;
// import org.usfirst.frc.team6822.robot.subsystems.LinearSlide;

import org.usfirst.frc.team6822.robot.commands.*;
import org.usfirst.frc.team6822.robot.subsystems.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
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

public class Robot extends TimedRobot 
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
        
        /*
        int i = 0;
		for(VideoMode vm : theCamera.enumerateVideoModes())
		{
			System.out.println((i++)+": "+vm.fps+" "+vm.height+" "+vm.width);
		}*/
		//theCamera.setVideoMode(VideoMode.)
		
		//CameraServer.getInstance().

		m_oi = new OI();
		m_intake = new Intake();
		m_linearslide = new LinearSlide();
		m_drivetrain = new DriveTrain();
        m_claws = new Claws();
        m_platform = new Platform();
        m_gripper = new Gripper();

		//m_chooser.addDefault("Default Auto", new AutoCommand());//to be done by Esha
		// chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", m_chooser)
		
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
		//m_autonomousCommand = m_chooser.getSelected();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
        
        /*
		boolean isSimple = true;
        if(isSimple)
		{  
			CommandGroup routine = new CommandGroup();
			//routine.addSequential(new AutoDriveControl(0.45,-0.08,true,1500));
			//routine.addParallel(new AutoLinear(0.3,700));
			routine.addSequential(new AutoDriveControl(0.5,0.00,true,2000));
			//routine.addParallel(new AutoLinear(-0.3,500));
			
			routine.start();
            
            //start comment block
            if(gameData.charAt(0)=='L')
			{
				CommandGroup routine = new CommandGroup();
				routine.addParallel(new AutoLinear(0.3,1000));
				routine.addSequential(new AutoDriveControl(0.6,0.00,true,2500));
				routine.start();
			}
			else
			{
				CommandGroup routine = new CommandGroup();
				routine.addSequential(new AutoDriveControl(0.45,-0.08,true,1500));
				routine.addSequential(new AutoDriveControl(0.45,0.00,true,2500));
				routine.start();
			} //end comment block
			if(isSimple)
			{
				return;
			}
		}
		else
		{
			return;
        }
        */

		//String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		// need to hardcode before each match (cannot modify ds location at competition)
		int LOCATION = DriverStation.getInstance().getLocation();
		//location 1=left 2=center 3=right
		
		long timeSlideUpSwitch = 1000;
		double voltageSlideUpSwitch = -0.5;
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
		
		
		
		boolean isStupid = false;
		if(isStupid)//stupid plan
		{
			/*
			Record commands beforehand and run them
            */
            int position = LOCATION-1;
            boolean isOnRight = gameData.charAt(0)=='R';
            if(isOnRight)
            {
                position+=3;
            }
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
			
			//System.out.println(m_autonomousCommand.getName());
			/*double time = System.currentTimeMillis();
			while (System.currentTimeMillis() - time < 2000) 
			{
				Robot.m_drivetrain.move(0.5, 0.5);
			}*/
			//System.out.println(gameData);
			if(gameData.length() > 0)
			{
				
				if(LOCATION == 1)//LEFT
				{
					if(gameData.charAt(0) == 'L')
					{
						// 14 feet forward, ~2 feet down (go up 1 foot first then go down 3 feet fastish)
						//turning is to adjust for motor auto turning (although its not adjusted properly)
						//this is not doing jank auto yet
						routine.addSequential(new AutoDriveControl(0.6, -0.05, true, 1500));
						routine.start();
						
						/*
						routine.addParallel(new AutoLinear(0.5,1000));
						routine.addSequential(new AutoDriveControl(0.4,SmartDashboard.getNumber("Angle", 0.05),true,(long) SmartDashboard.getNumber("Length1", 1000)));
						routine.addSequential(new AutoDriveControl(0.4,-SmartDashboard.getNumber("Angle", 0.05),true,(long) SmartDashboard.getNumber("Length2", 500)));
						

						//routine.addSequential(new AutoIntake(false,500));
						routine.start();
						*/
						
						
					}
					else
					{
						routine.addSequential(new AutoDriveControl(0.6, -0.05, true, 1500));
						routine.start();
						/*
						//CommandGroup routine = new CommandGroup();
						routine.addParallel(new AutoLinear(0.8,2000));
						routine.addSequential(new AutoDriveControl(0.5,-0.15,true,1500));
						routine.addSequential(new AutoDriveControl(0.5,0.1,true,2000));
						routine.start();
						*/
					}
				}
				else if(LOCATION==2) //central
				{
					if(gameData.charAt(0) == 'L')
					{
						routine.addSequential(new AutoDriveControl(0.6, 0.07, true, 1500));
						routine.start();
						/*
						//CommandGroup routine = new CommandGroup();
						routine.addParallel(new AutoLinear(0.5,1000));
						routine.addSequential(new AutoDriveControl(0.4,0,true,500));
						routine.addSequential(new AutoDriveControl(0.4,-0.5,true,(long) SmartDashboard.getNumber("Length1", 500)));
						routine.addSequential(new AutoDriveControl(0.5,0.1,true,(long) SmartDashboard.getNumber("Length2", 500)));
	
						//routine.addSequential(new AutoIntake(false,1000));
						routine.start();
						*/
						
					}
					else 
					{
						routine.addSequential(new AutoDriveControl(0.6, 0.07, true, 1500));
						routine.start();
						/*
						//CommandGroup routine = new CommandGroup();
						//routine.addParallel(new AutoLinear(0.8,2000));
						//routine.addSequential(new AutoDriveControl(0.4,0.2,true,1000));
						routine.addSequential(new AutoDriveControl(0.4,0.03,true,1000));
						routine.addSequential(new AutoDriveControl(0.4,-0.03,true,1000));
						//routine.addSequential(new AutoIntake(false,1000));
						routine.start();
						*/
					}
				}
				else if(LOCATION==3) //right
				{
					if(gameData.charAt(0) == 'L')
					{
						routine.addSequential(new AutoDriveControl(0.6, -0.05, true, 1500));
						routine.start();
						/*
						//CommandGroup routine = new CommandGroup();
						routine.addParallel(new AutoLinear(0.8,2000));
						routine.addSequential(new AutoDriveControl(0.5,0.15,true,1500));
						routine.addSequential(new AutoDriveControl(0.5,-0.1,true,2000));
						routine.start();
						*/
						
					}
					else 
					{
						routine.addSequential(new AutoDriveControl(0.6, -0.05, true, 1500));
						routine.start();
						/*
						//CommandGroup routine = new CommandGroup();
						routine.addParallel(new AutoLinear(0.5,1000));
						routine.addSequential(new AutoDriveControl(0.4,-SmartDashboard.getNumber("Angle", 0.05),true,(long) SmartDashboard.getNumber("Length1", 1000)));
						routine.addSequential(new AutoDriveControl(0.4,SmartDashboard.getNumber("Angle", 0.05),true,(long) SmartDashboard.getNumber("Length2", 500)));
	
						//routine.addSequential(new AutoIntake(false,500));
						routine.start();
						*/
					}
				}
			
				/*
				double time = System.currentTimeMillis();
				if(RobotMap.gameData.charAt(0) == 'R')
				{
					while (System.currentTimeMillis() - time < 5000) 
					{
						Robot.m_drivetrain.move(0.5, 0.5);
						if(System.currentTimeMillis() - time < 1000)
						{
							Robot.m_linearslide.move(0.5);
						}
						[
			    		Robot.m_drivetrain.moveWithCurve(0.5, -90, true);
					}
					
					while (System.currentTimeMillis() - time < 2000) 
					{
						Robot.m_drivetrain.move(0.5, 0.5);
					}
					while (System.currentTimeMillis() - time < 2000) 
					{
						Robot.m_intake.moveMotors(-0.5);
					}
				}
				else 
				{
					while (System.currentTimeMillis() - time > 5000) 
					{
						Robot.m_drivetrain.move(0.5, 0.5);
					}
				}*/
			}
			else
			{
				System.out.println("PLEASE SPECIFY GAMEDATA");
			}
			/*RobotMap.gameData = "";
			while(RobotMap.gameData.length() < 3) 
			{
				try {
				RobotMap.gameData = DriverStation.getInstance().getGameSpecificMessage();
				}
				catch (Exception E) {}
				if (m_autonomousCommand != null) 
				{
					m_autonomousCommand.start();
				}
			}*/
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
    	
    	
		
		
		//left autonomous code do not touchy it's bad
		/*if(RobotMap.gameData.length() > 0)
		{
			double time = System.currentTimeMillis();
			if(RobotMap.gameData.charAt(0) == 'L')
			{
				while (System.currentTimeMillis() - time < 5000) 
				{
					Robot.m_drivetrain.move(0.5, 0.5);
					if(System.currentTimeMillis() - time < 1000)
					{
						Robot.m_linearslide.move(0.5);
					}
					
		    		Robot.m_drivetrain.moveWithCurve(0.5, 90, true);
				}
				
				while (System.currentTimeMillis() - time < 2000) 
				{
					Robot.m_drivetrain.move(0.5, 0.5);
				}
				while (System.currentTimeMillis() - time < 2000) 
				{
					Robot.m_intake.moveMotors(-0.5);
				}
			}
			else 
			{
				while (System.currentTimeMillis() - time > 5000) 
				{
					Robot.m_drivetrain.move(0.5, 0.5);
				}
			}
		}
		
		//right autonomous code
		if(RobotMap.gameData.length() > 0)
		{
			double time = System.currentTimeMillis();
			if(RobotMap.gameData.charAt(0) == 'R')
			{
				while (System.currentTimeMillis() - time < 5000) 
				{
					Robot.m_drivetrain.move(0.5, 0.5);
					if(System.currentTimeMillis() - time < 1000)
					{
						Robot.m_linearslide.move(0.5);
					}
					
		    		Robot.m_drivetrain.moveWithCurve(0.5, -90, true);
				}
				
				while (System.currentTimeMillis() - time < 2000) 
				{
					Robot.m_drivetrain.move(0.5, 0.5);
				}
				while (System.currentTimeMilli yufs() - time < 2000) 
				{
					Robot.m_intake.moveMotors(-0.5);
				}
			}
			else 
			{
				while (System.currentTimeMillis() - time > 5000) 
				{
					Robot.m_drivetrain.move(0.5, 0.5);
				}
			}
		}*/
	
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
                // + Robot.m_oi.joystick.getRawAxis(Robot.m_oi.lClawAxis) + ","
                // + Robot.m_oi.joystick.getRawAxis(Robot.m_oi.rClawAxis) + ","
                //+ (Robot.m_oi.intakein.get() ? 1.0 : 0.0) + ","
                //+ (Robot.m_oi.intakeout.get() ? 1.0 : 0.0)
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