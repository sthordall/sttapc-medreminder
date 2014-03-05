package org.sttapc.medreminder.util;

import org.sttapc.medreminder.context.Schedule;
import org.sttapc.medreminder.context.Logning;
import org.sttapc.medreminder.context.StateProvider;

public class Configurator {

	/*
	 * Configuration associated with MotionSensor
	 */
	private int motionSensitivity;

	private int motionSensitivityReset;

	public int getMotionSensitivity() {
		return motionSensitivity;
	}

	public void setMotionSensitivity(int motionSensitivity) {
		this.motionSensitivity = motionSensitivity;
	}

	public int getMotionSensitivityReset() {
		return motionSensitivityReset;
	}

	public void setMotionSensitivityReset(int motionSensitivityReset) {
		this.motionSensitivityReset = motionSensitivityReset;
	}
	
	/*
	 * Configuration associated with MagneticHandler
	 */
	
	int outputOne = -1;
	int outputTwo = -1;
	private Logning logning;
	
	public Logning getLogning() {
		return logning;
	}

	public void setLogning(Logning logning) {
		this.logning = logning;
	}

	public int getOutputOne() {
		return outputOne;
	}

	public void setOutputOne(int outputOne) {
		this.outputOne = outputOne;
	}

	public int getOutputTwo() {
		return outputTwo;
	}

	public void setOutputTwo(int outputTwo) {
		this.outputTwo = outputTwo;
	}
	
	/*
	 * Configuration associated with Adherence
	 */
	
	int Points; 

	public int getPoints() {
		return Points;
	}

	public void setPoints(int points) {
		Points = points;
	}

	/*
	 * Configuration associated with Adherence 
	 */
	private Schedule schedule;

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	/*
	 * General fields
	 */
	private StateProvider stateProvider = StateProvider.getInstance();

	private Reminder reminder;

	public StateProvider getStateProvider() {
		return stateProvider;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}

	public Reminder getReminder() {
		return reminder;
	}
}
