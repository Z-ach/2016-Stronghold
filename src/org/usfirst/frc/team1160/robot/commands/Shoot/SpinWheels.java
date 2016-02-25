package org.usfirst.frc.team1160.robot.commands.Shoot;

import org.usfirst.frc.team1160.robot.Robot;
import org.usfirst.frc.team1160.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class SpinWheels extends Command implements RobotMap{

	double rpm, timeElapsed;
	
	public SpinWheels(){
		requires(Robot.shoot);
	}
	@Override
	protected void initialize() {
		rpm = Robot.shoot.addEnergy();
	}

	@Override
	protected void execute() {
		Robot.shoot.setFlywheel(rpm);
	}

	@Override
	protected boolean isFinished() {
		return Robot.shoot.isComplete(rpm);
	}

	@Override
	protected void end() {
		Robot.shoot.setFlywheel(0);
	}

	@Override
	protected void interrupted() {
		
	}

}
