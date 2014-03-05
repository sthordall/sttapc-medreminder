package org.sttapc.medreminder.util;

import org.sttapc.medreminder.context.Schedule;
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
