package org.sttapc.medreminder.context;

import java.util.Date;

import org.sttapc.medreminder.util.Configurator;

public class Adherence {

	int hourWithInRange = 1;
	int hourRangeOne = 2;
	int hourRangeTwo = 3;
	
	Schedule schedule;
	
	public Adherence(Schedule schedule){
		this.schedule = schedule;
	}
	
	/**
	 * Calculate points from the Ideal Date and the current Date 
	 */
	@SuppressWarnings("deprecation")
	public Integer CalculatePoints(Date currentDate){
		//Date currentDate = new Date();
		
		//The Time range that is fully adhered
		Date lowerIdealDate = schedule.getIdealDate();
		Date upperIdealDate = schedule.getIdealDate();
		
		lowerIdealDate.setHours(schedule.getIdealDate().getHours() - hourWithInRange);
		upperIdealDate.setHours(schedule.getIdealDate().getHours() + hourWithInRange);
				
		if(upperIdealDate.before(currentDate) && lowerIdealDate.after(currentDate) ){
			return 3;
		}
		
		lowerIdealDate.setHours(schedule.getIdealDate().getHours() - hourRangeOne);
		upperIdealDate.setHours(schedule.getIdealDate().getHours() + hourRangeOne);
		
		if((schedule.getIdealDate().getHours() + hourRangeOne < currentDate.getHours()) ){
			return 2;
		}
		
		lowerIdealDate.setHours(schedule.getIdealDate().getHours() - hourRangeTwo);
		upperIdealDate.setHours(schedule.getIdealDate().getHours() + hourRangeTwo);
		
		if((schedule.getIdealDate().getHours() + hourRangeOne < currentDate.getHours()) ){
			return 1;
		}
		else {
			return 0;
		}
	}
}
