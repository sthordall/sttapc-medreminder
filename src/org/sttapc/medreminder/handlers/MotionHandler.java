package org.sttapc.medreminder.handlers;

import java.util.Date;

import org.sttapc.medreminder.context.Logning;
import org.sttapc.medreminder.context.State;
import org.sttapc.medreminder.context.StateProvider;
import org.sttapc.medreminder.util.Configurator;
import org.sttapc.medreminder.util.Reminder;

import com.phidgets.event.SensorChangeEvent;
import com.phidgets.event.SensorChangeListener;

/**
 * Handles everything associated with the motionsensor.
 */
public class MotionHandler {

	/**
	 * Number of detections needed before handling.
	 */
	private int sensitivity;

	/**
	 * Seconds before DetectionCounter is reset.
	 */
	private int sensitivityReset;

	private Date latestDetectionDate;
	private int detectionCounter;

	private StateProvider stateProvider;
	private Reminder reminder;
	private Logning logning;

	private SensorChangeListener sensorChangeListener = new SensorChangeListener() {

		@Override
		public void sensorChanged(SensorChangeEvent arg0) {
			if (doHandleEvent()) {
				HandleEvent(stateProvider.getState());
			}
		}
	};

	public SensorChangeListener getSensorChangeListener() {
		return sensorChangeListener;
	}

	public MotionHandler(Configurator configurator) {
		sensitivity = configurator.getMotionSensitivity();
		sensitivityReset = configurator.getMotionSensitivityReset();
		stateProvider = StateProvider.getInstance();
		reminder = configurator.getReminder();
		logning = configurator.getLogning();
		latestDetectionDate = new Date();
		detectionCounter = 0;
	}

	private void HandleEvent(State state) {
		System.out.println("MotionHandler: Handling motion - " + state);
		try {
			switch (state) {
			case IDLE:
				break;
			case ACTIVE:
				logning.LogForMotionHandler(new Date(), state);
				reminder.NearbyActiveReminder();
				break;
			case WARNING:
				logning.LogForMotionHandler(new Date(), state);
				reminder.NearbyWarningReminder();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Determines if the event needs handling, based on set Sensitivity and
	 * SensitivityReset.
	 * 
	 * @return boolean
	 */
	private boolean doHandleEvent() {
		Date newDetectionDate = new Date();
		long diff = newDetectionDate.getTime() - latestDetectionDate.getTime();
		long diffSeconds = diff / 1000;

		if (diffSeconds >= sensitivityReset) {
			detectionCounter = 0;
		}

		latestDetectionDate = new Date();

		if (detectionCounter >= sensitivity) {
			detectionCounter = 0;
			return true;
		} else {
			detectionCounter++;
			return false;
		}
	}
}
