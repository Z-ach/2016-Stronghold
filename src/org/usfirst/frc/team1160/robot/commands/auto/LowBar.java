package org.usfirst.frc.team1160.robot.commands.auto;

import org.usfirst.frc.team1160.robot.RobotMap;
import org.usfirst.frc.team1160.robot.commands.Drive;
import org.usfirst.frc.team1160.robot.commands.air.PickupPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBar extends CommandGroup implements RobotMap{
    
    public  LowBar() {
    	addSequential(new PickupPosition());
    	addSequential(new Drive(LOWBAR_DISTANCE));
    }
}
