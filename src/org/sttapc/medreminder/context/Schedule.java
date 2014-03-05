package org.sttapc.medreminder.context;

import java.util.Date;

/**
 * This class is responsible to configure a cycling schedule for whether the
 * user should be reminded or not in the given state it is in. The class is
 * considered to be a model containing data This class shall return the
 * configuration to MagneticHandler
 */
public class Schedule {

	/*------------------------------Properties---------------------------------------------*/
	private Date idealDate;
	private Date startActiveDate;
	private Date startWarningDate;
	private Date startIdleDate;

	private static long idealToActiveInterval = 60000 * 15; // 15 minutes
	private static long activeToWarningInterval = 60000 * 105; // 105 minutes
	private static long warningToIdleInterval = 60000 * 60; // 60 minutes

	/*------------------------------Constructors-------------------------------------------*/
	/**
	 * TODO: this constructor should read configuration based from a file and
	 * set the properties for a schedule.
	 */
	public Schedule() {

	}

	public Schedule(Date ideal) {
		idealDate = ideal;
		startActiveDate = new Date(idealDate.getTime() + idealToActiveInterval);
		startWarningDate = new Date(startActiveDate.getTime()
				+ activeToWarningInterval);
		startIdleDate = new Date(startWarningDate.getTime()
				+ warningToIdleInterval);
	}

	/*------------------------------Business Logic Methods---------------------------------*/
	// TODO: No business logic is needed so far...

	/*------------------------------Getters and Setters------------------------------------*/

	public Date getIdealDate() {
		return idealDate;
	}

	public void setIdealDate(Date idealDate) {
		this.idealDate = idealDate;
	}

	public Date getStartActiveDate() {
		return startActiveDate;
	}

	public void setStartActiveDate(Date startActiveDate) {
		this.startActiveDate = startActiveDate;
	}

	public Date getStartWarningDate() {
		return startWarningDate;
	}

	public void setStartWarningDate(Date startWarningDate) {
		this.startWarningDate = startWarningDate;
	}

	public Date getStartIdleDate() {
		return startIdleDate;
	}

	public void setStartIdleDate(Date startIdleDate) {
		this.startIdleDate = startIdleDate;
	}

}
