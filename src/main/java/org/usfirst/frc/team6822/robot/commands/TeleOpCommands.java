package org.usfirst.frc.team6822.robot.commands;

import org.usfirst.frc.team6822.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TeleOpCommands extends CommandGroup {

    public TeleOpCommands() {
       addParallel(new DriveTrainControl());
       addParallel(new LinearSlideControl());
       addParallel(new IntakeControl());
       addParallel(new ClawsControl());
       addParallel(new PlatformControl());
    }
}
