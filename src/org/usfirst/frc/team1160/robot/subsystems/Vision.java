package org.usfirst.frc.team1160.robot.subsystems;

import org.usfirst.frc.team1160.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Vision extends Subsystem implements RobotMap{


	public static Vision instance;
	public Timer timer;
	public NetworkTable table;
	
	public double[] areas, centerY, centerX, height, width, defaultValue;
	
	public static Vision getInstance(){
		if (instance == null){
			instance = new Vision();
		}
		return instance;
	}
	
	public Vision(){
		timer = new Timer();
		table = NetworkTable.getTable("GRIP/myContoursReport");
		defaultValue = new double[0];
	}
	
	public boolean align(){
			for(int i = 0;i < centerY.length; i++){
			if (centerY[i] <= X_MAX && centerY[i] >= X_MIN){
				return true;			
				}
			}
			return false;
	}
	
	public void visualize(){
		table = NetworkTable.getTable("GRIP/myContoursReport");
		
		double[] defaultValue = new double[0];
		areas = table.getNumberArray("area", defaultValue);
		System.out.println("areas: ");
		for (double area : areas) {
			System.out.println(area + " ");
			
		}
		centerY = table.getNumberArray("centerY", defaultValue);
		System.out.println("centerY: ");
		for (double centersY : centerY) {
			System.out.println(centersY + " ");
		}
		centerX = table.getNumberArray("centerX", defaultValue);
		System.out.println("centerX: ");
		for (double centersX : centerX) {
			System.out.println(centersX + " ");
		}
		height = table.getNumberArray("height", defaultValue);
		System.out.println("height: ");
		for (double heights : height) {
			System.out.println(heights + " ");
		}
		width = table.getNumberArray("width", defaultValue);
		System.out.println("width: ");
		for (double widths : width) {
			System.out.println(widths + " ");
		}
		System.out.println();	

	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}