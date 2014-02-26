package org.sttapc.medreminder.util;

import org.sttapc.medreminder.context.StateProvider;

// TODO: Handle user configuration

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
	 * General fields
	 */
	private StateProvider stateProvider = StateProvider.getInstance();

	private Reminder reminder;

	public StateProvider getStateProvider() {
		return stateProvider;
	}

	public Reminder getReminder() {
		return reminder;
	}
}
