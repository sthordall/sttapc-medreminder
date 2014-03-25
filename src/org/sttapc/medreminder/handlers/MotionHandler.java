package org.sttapc.medreminder.handlers;

import java.util.Date;

import org.sttapc.medreminder.context.Logning;
import org.sttapc.medreminder.context.State;
import org.sttapc.medreminder.context.StateProvider;
import org.sttapc.medreminder.util.Configurator;
import org.sttapc.medreminder.util.Reminder;

import com.phidgets.event.SensorChangeEvent;
import com.phidgets.event.SensorChangeListener;
import com.sun.tools.internal.jxc.apt.Const;

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
	private int countOnSoundVolume = 0;
	private Date latestDetectionDate;
	private int detectionCounter;
	private int IntervalFornextNotifyInMinutes = 10;
	Date referenceDate = new Date();

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
		//TODO: after it has been tested, should have it initialized by coonfigurator
		SetIntervalDateInMinutes();
	}
	
	/**
	 * When used, we set the interval when the sounds need to play
	 * It has to be independent of the logning functionally
	 * @param date
	 */
	private boolean IntervalSoundPlay(Date currentDate){
		System.out.println("IntervalSoundPlay called...");
		
		System.out.println(referenceDate.toString());
			
		if(currentDate.before(referenceDate)){
			System.out.println(false);
			return false;
		}
		else{
			//Set the next interval
			SetIntervalDateInMinutes();		
			System.out.println(true);
			return true;
		}
		//
	}
	
	/**
	 * Must be called when class is initialized and when it needs a new reset
	 */
	private void SetIntervalDateInMinutes(){
		System.out.println("SetIntervalDateInMinutes");
		referenceDate.setMinutes(referenceDate.getMinutes() + IntervalFornextNotifyInMinutes);
	}

	private void HandleEvent(State state) {
		System.out.println("MotionHandler: Handling motion - " + state);
		try {
			switch (state) {
			case IDLE:
				break;
			case ACTIVE:
				logning.LogForMotionHandler(new Date(), state);
				if(IntervalSoundPlay(new Date())){
					reminder.NearbyActiveReminder();
				}
				
				break;
			case WARNING:			
				Date date = new Date();
				logning.LogForMotionHandler(date, state);
				if(IntervalSoundPlay(new Date())){
				reminder.NearbyWarningReminder(countOnSoundVolume);
				//This is used for executing different soundfile that had different soundvolume.
					if(countOnSoundVolume < 5){
						System.out.println("count is: " +countOnSoundVolume);
							countOnSoundVolume++;
							reminder.NearbyWarningReminder(countOnSoundVolume);
						}	
					}
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
