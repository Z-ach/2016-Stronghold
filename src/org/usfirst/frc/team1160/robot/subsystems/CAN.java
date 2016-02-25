package org.usfirst.frc.team1160.robot.subsystems;

import org.usfirst.frc.team1160.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;

public class CAN extends CANTalon implements RobotMap{

	public CAN(int deviceNumber, int kP, int kI, int kD, boolean flip) {
		super(deviceNumber);
		setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		changeControlMode(CANTalon.TalonControlMode.Speed);
		set(0);
		reverseSensor(flip);
		setPID(kP, kI, kD);
	}
	
	public boolean isDone(double goal){
		if(Math.abs((double) getClosedLoopError() - goal) > ABS_TOL)
			return true;
		return false;
	}

}
