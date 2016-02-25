package org.usfirst.frc.team1160.robot.subsystems;

import org.usfirst.frc.team1160.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem implements RobotMap{
	
	public static Shooter instance;
	
	protected final CAN big, small;
	private double rpm, angleSec, finalRPM, smallCurrentRPM, largeCurrentRPM, logVel, smallRev, largeRev;
	private Vision vision;
	
	public static Shooter getInstance(){
		if(instance == null){
			instance = new Shooter();
		}
		return instance;
	}
	
	private Shooter(){
		big = new CAN(S_FLYWHEEL_LARGE, kP, kI, kD, true);
		small = new CAN(S_FLYWHEEL_SMALL, kP, kI, kD, false);
		vision = Vision.getInstance();
		SmartDashboard.putNumber("TEST_DISTANCE", TEST_DISTANCE);
	}
	
	public void setFlywheel(double speed){
		big.set(speed);
		small.set(speed);
		SmartDashboard.putNumber("Top Shooter Wheel",big.getSpeed());
		SmartDashboard.putNumber("Bottom Shooter Wheel",small.getSpeed());
	}
	
	public void reset(){
		small.reset();
		big.reset();
	}
	
	public boolean isComplete(double goal){
		return (big.isDone(goal) && small.isDone(goal));
	}
	
	public void setFlyPID(double RPM){
		
	}
	
	public void getRevolutions(){
		smallRev = small.get();
		largeRev = big.getPosition() / 4096;
		System.out.println("Small: " + smallRev);
		System.out.println("Large: " + largeRev);
		System.out.println("LargeRPM: " + big.getSpeed() * 600 / 4096);
	}
	
	public void bangBang(double targetRPM){
		smallCurrentRPM = small.getSpeed() * 600 / 4096;
		largeCurrentRPM = big.getSpeed() * 600 / 4096;
		SmartDashboard.putNumber("Bottom Wheel RPM: ", smallCurrentRPM);
		SmartDashboard.putNumber("Top Wheel RPM: ", largeCurrentRPM);
		SmartDashboard.putNumber("Goal RPM: ", targetRPM);
	}
	
	protected void initDefaultCommand() {
		
	}

	public void testFire(){
		//SmartDashboard.putNumber("CANTalon Big: ", big.getEncVelocity());
		//SmartDashboard.putNumber("CANTalon Small: ", small.getEncVelocity());
	}
	
	//--------------------Calculations stuff---------------------
	public double speedFromDistance(double distance){
		angleSec = 1/Math.cos(SHOOTER_ANGLE_RADIANS);
		rpm = ((distance*angleSec*Math.sqrt((GRAVITATIONAL_ACCEL)/(2*(BALL_VERTICAL_DISPLACEMENT - distance*Math.tan(SHOOTER_ANGLE_RADIANS)))))/SHOOTER_WHEEL_CIRCUMFERENCE)*60;
		return rpm;
	}
	
	public double velocity(double distance){
		angleSec = 1/Math.cos(SHOOTER_ANGLE_RADIANS);
		logVel = FT_TO_M*(distance*angleSec*Math.sqrt((GRAVITATIONAL_ACCEL)/(2*(BALL_VERTICAL_DISPLACEMENT - distance*Math.tan(SHOOTER_ANGLE_RADIANS)))));
		SmartDashboard.putNumber("Goal Velocity Set At: ", logVel);
		return logVel;
	} 
	
	public double addEnergy(){
		finalRPM = speedFromDistance(vision.getDistance()) + 102.788*velocity(vision.getDistance());
		return finalRPM;
	}
}
