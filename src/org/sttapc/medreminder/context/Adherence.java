package org.sttapc.medreminder.context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.sttapc.medreminder.util.Configurator;

public class Adherence {
	int points;
	Schedule schedule;
	
	public Adherence(Configurator configurator){
		this.schedule = configurator.getSchedule();
	}
	
	/**
	 * Calculate points from the Ideal Date and the current Date 
	 */
	public void CalculatePoints(){
		
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	public int getPoints() {
		return points;
	}	



}
