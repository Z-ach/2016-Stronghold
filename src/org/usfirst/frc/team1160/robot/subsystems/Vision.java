package org.usfirst.frc.team1160.robot.subsystems;

import org.usfirst.frc.team1160.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision extends Subsystem implements RobotMap{


	private static Vision instance;
	
	private double[] centerY, centerX, width, defaultValue;
	private double distance;
	public NetworkTable table;
	
	
	public static Vision getInstance(){
		if (instance == null){
			instance = new Vision();
		}
		return instance;
	}
	
	private Vision(){
		defaultValue = new double[0];
		centerX = new double[defaultValue.length];
		centerY = new double [defaultValue.length];
	}
	
	private void refresh(){
		table = NetworkTable.getTable("GRIP/myContoursReport");
	}
	
	public boolean alignCheck(){
		refresh();
		centerX = table.getNumberArray("centerX", defaultValue);
		centerY = table.getNumberArray("centerY", defaultValue);
		
		for(int i = 0;i < centerY.length; i++){
			if (centerY[i] <= Y_MAX && centerY[i] >= Y_MIN && centerX[i] <= X_MAX && centerX[i] >= X_MIN){
				return true;			
				}
			}
			return false;
	}
	
	/**************************************************************************************************
	 * http://www.pyimagesearch.com/2015/01/19/find-distance-camera-objectmarker-using-python-opencv/
	 * Basically fractions: 
	 * uses apparent size of image (pixels)
	 * uses actual size of object (inches)
	 * uses focal length of camera (preset conditions)
	 **************************************************************************************************/
	public double getDistance(){
		refresh();
		width = table.getNumberArray("width", defaultValue);
		if(width.length > 0)
			distance = (FOCAL_X*WIDTH_ACTUAL/width[0]) / 12;
		else
			distance = TEST_DISTANCE;
		SmartDashboard.putNumber("Distance Recorded as: ", distance);
		return distance;
	}
	
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
