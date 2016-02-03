package org.usfirst.frc.team1160.robot.commands;

import org.usfirst.frc.team1160.robot.Robot;
import org.usfirst.frc.team1160.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Detect extends Command{

	public Detect(){
		requires(Robot.see);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.see.visualize();
	}

	@Override
	protected boolean isFinished() {
		return Robot.see.align();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	

}