package org.sttapc.medreminder.util;

import java.util.Date;

import com.sun.xml.internal.bind.v2.TODO;

/**
 * This class is responsible to configure a cycling schedule
 * for whether the user should be reminded or not in the given 
 * state it is in.
 * The class is considered to be a model containing data
 * This class shall return the configuration to MagneticHandler
 */
public class Schedule {

/*------------------------------Properties---------------------------------------------*/
	private Date lowerDateInterval;
	private Date upperDateInterval;
	
/*------------------------------Constructors-------------------------------------------*/	
	/**
	 * TODO: this constructor should read configuration based from a file and
	 * set the properties for a schedule.
	 */
	public Schedule(){
		
	}
	
	public Schedule(Date lowerInterval, Date upperInterval){
		lowerDateInterval = lowerInterval;
		upperDateInterval = upperInterval;
	}
	
/*------------------------------Business Logic Methods---------------------------------*/
	//TODO: No business logic is needed so far...
	
/*------------------------------Getters and Setters------------------------------------*/
	
	public Date getLowerDateInterval() {
		return lowerDateInterval;
	}

	public void setLowerDateInterval(Date lowerDateInterval) {
		this.lowerDateInterval = lowerDateInterval;
	}

	public Date getUpperDateInterval() {
		return upperDateInterval;
	}

	public void setUpperDateInterval(Date upperDateInterval) {
		this.upperDateInterval = upperDateInterval;
	}	
	
	

}
