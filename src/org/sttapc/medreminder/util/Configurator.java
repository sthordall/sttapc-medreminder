package org.sttapc.medreminder.util;

import org.sttapc.medreminder.context.State;
import org.sttapc.medreminder.context.StateProvider;

// TODO: Persist an load data via JSON.

public class Configurator {

	/*
	 * Configuration associated with MotionSensor
	 */
	private int motionSensitivity;

	private int motionSensitivityReset;

	public int getMotionSensitivity() {
		return motionSensitivity;
	}

	private void setMotionSensitivity(int motionSensitivity) {
		this.motionSensitivity = motionSensitivity;
	}

	public int getMotionSensitivityReset() {
		return motionSensitivityReset;
	}

	private void setMotionSensitivityReset(int motionSensitivityReset) {
		this.motionSensitivityReset = motionSensitivityReset;
	}

	public void setupMotionHandler() {
		getStateProvider().setState(State.CONFIGURING);
		// TODO: Load motionhandler setup data from some JSON
		// setMotionSensitivity(motionSensitivityFromJSON);
		// setMotionSensitivityReset(motionSensitivityResetFromJSON);
		getStateProvider().setState(State.ACTIVE);
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
