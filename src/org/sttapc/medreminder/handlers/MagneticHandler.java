package org.sttapc.medreminder.handlers;

import java.util.Date;

import org.sttapc.medreminder.context.StateProvider;
import org.sttapc.medreminder.util.Configurator;
import org.sttapc.medreminder.util.Reminder;
import org.sttapc.medreminder.util.Schedule;

import apple.laf.JRSUIConstants.State;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.InputChangeEvent;
import com.phidgets.event.InputChangeListener;
/**
 *
 */
public class MagneticHandler {

	private InterfaceKitPhidget interfaceKitPhidget;

	// Properties for input an output setup... necessary?
	int outputOne = -1;
	int outputTwo = -1;
	private boolean inputData;
	private Date latestDetectionDate;
	private Date newestDetectionDate;
	private Schedule schedule;
	
	// States
	//private StateProvider stateProvider;
	private Reminder reminder;

	/*------------------------------Constructors-------------------------------------------*/

	public MagneticHandler(InterfaceKitPhidget interfaceKitPhidget, Schedule schedule) throws PhidgetException {
		this.interfaceKitPhidget = interfaceKitPhidget;
		outputOne = 1;
		outputTwo = 0;
		this.schedule = schedule;
		
		System.out.println("MagneticHandler instantiated...");
	}

	public MagneticHandler(InterfaceKitPhidget interfaceKitPhidget, int outputOne, int outputTwo, Schedule schedule)
			throws PhidgetException {

		this.interfaceKitPhidget = interfaceKitPhidget;
		interfaceKitPhidget.setOutputState(outputOne, false);
		interfaceKitPhidget.setOutputState(outputTwo, false);
		
		this.schedule = schedule;
		
		System.out.println("MagneticHandler instantiated...");
	}
	

	/*------------------------------Constructors-------------------------------------------*/

	/**
	 * Private method to initialize the input and attach a listener to the input
	 * This method implements an overrided method from InputChangeListener
	 */
	private InputChangeListener inputChangeListener = new InputChangeListener() {
		@Override
		public void inputChanged(InputChangeEvent arg0) {
			System.out.println("InputChanged...");

			System.out.println("Input value : " + arg0.getState()
					+ " on index: " + arg0.getIndex());

			setInputData(arg0.getState());
			// Do something here..logging and notify that the user has opened
			// the
			// case and thereby taken his medicine?
			CaseIsOpened(arg0.getState());
			IfCaseIsClosedRightAfter();
		}
	};
	
	public void AttachMagneticHandler(){
		interfaceKitPhidget.addInputChangeListener(getInputChangeListener());
	}

	public InputChangeListener getInputChangeListener() {
		return inputChangeListener;
	}

	public void setInputChangeListener(InputChangeListener inputChangeListener) {
		this.inputChangeListener = inputChangeListener;
	}
	
	
	/**
	 * Depending on what state the system is in, it has to handle coordinately
	 * This method is responsible for handling to a corresponding state
	 * TODO: This method should be called after validation of correct user interaction
	 * 
	 * 
	 * If that is not implemented this should be handled in each switch case.
	 */
	private void HandleState(){
		Date currentTime =  new Date();
		org.sttapc.medreminder.context.State currentState = StateProvider.getInstance().getState();
		switch(currentState){
		/* If it is in active state, and is within the schedule,
		 * it changes state to IDLE
		 * Log info to a xml file.
		 */
		case ACTIVE:
			if(schedule.getLowerDateInterval().before(currentTime) && schedule.getUpperDateInterval().after(currentTime)){
				StateProvider.getInstance().setState(org.sttapc.medreminder.context.State.IDLE);
				LogOnActiveAndWithinSchedule();
				break;
			}
			else{
				StateProvider.getInstance().setState(org.sttapc.medreminder.context.State.WARNING); //WARNING? IDLE?
				LogOnActiveAndNotWithinSchedule();
				break;
			}
		/* If in IDLE state, this means the patients has opened
		 * the case outside the schedule. This does not necessarily means
		 * that the patients is about to take the medicine
		 * THis is because we handle,
		 * 
		 */
		case IDLE:
			break;
		case WARNING:
			break;
		default:
			break;
		
		}
		
	}

	/**
	 * Sets whether the case is closed or opened. Also sets the date for when the event was executed
	 * StateProvider contains info on if case is closed/opened
	 * false: case closed, true: case opened
	 */
	private void CaseIsOpened(boolean input) {
		latestDetectionDate = new Date();
		StateProvider.getInstance().setIsCaseClosed(input);
	}
		
	/**
	 * If the user immediately close the case right after opening, it would mean
	 * that the user has not taken the medicine out of the case
	 * 
	 * TODO: Either compare to a global Time, or create an iteration and notify
	 * if case is still not closed. This makes the magnetic sensor independent of other sensors
	 */
	private void IfCaseIsClosedRightAfter(){
		
	}
	
	
	/**
	 * (THIS METHOD MIGHT BE SPLIT INTO SEPERATE METHODS)
	 * This method shall write into xml file with validation
	 * Should only log for when user has taken medicine
	 * TODO((Only log on valid user interaction(ex. not log when case is opened
	 * and closed immediately after))
	 */
	private void LogInfo(org.sttapc.medreminder.context.State state, boolean IsWithinSchedule ){
		if(state == state.ACTIVE && IsWithinSchedule){
			
		}
	}
	
	private void LogOnActiveAndWithinSchedule(){
		
	}
	
	private void LogOnActiveAndNotWithinSchedule(){
		
	}
		
	/**
	 * If the user leaves the case opened, we can not correctly 
	 * determine if the user actually taken out the medicine
	 */
	private void AlarmUserToCloseCase(){
		
	}

	/*------------------------------Getters and Setters-------------------------------------------*/
	public int getOutputOne() {
		return outputOne;
	}

	public void setOutputOne(int outputOne) {
		this.outputOne = outputOne;
	}
	
	public boolean isInputData() {
		return inputData;
	}

	public void setInputData(boolean inputData) {
		this.inputData = inputData;
	}
	/*------------------------------Getters and Setters-------------------------------------------*/
}
