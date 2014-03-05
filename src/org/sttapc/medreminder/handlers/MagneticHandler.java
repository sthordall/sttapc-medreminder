package org.sttapc.medreminder.handlers;

import java.util.Date;

import javax.xml.transform.TransformerException;

import org.sttapc.medreminder.context.Adherence;
import org.sttapc.medreminder.context.Logning;
import org.sttapc.medreminder.context.State;
import org.sttapc.medreminder.context.StateProvider;
import org.sttapc.medreminder.util.Configurator;
import org.sttapc.medreminder.util.Reminder;
import org.sttapc.medreminder.context.Schedule;

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
	private Logning logning;
	Adherence adherence;
	
	// States
	//private StateProvider stateProvider;
	private Reminder reminder;

	/*------------------------------Constructors-------------------------------------------*/
	

	public MagneticHandler(InterfaceKitPhidget interfaceKitPhidget, Configurator configurator)
			throws PhidgetException {

		this.interfaceKitPhidget = interfaceKitPhidget;
		interfaceKitPhidget.setOutputState(configurator.getOutputOne(), false);
		interfaceKitPhidget.setOutputState(configurator.getOutputTwo(), false);
		
		this.schedule = configurator.getSchedule();
		this.logning = configurator.getLogning();
		adherence = new Adherence(configurator.getSchedule());
		
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
	 * example: If user wants to refill his case. If user accidentially opens it without
	 * taking the medication.
	 * 
	 */
	private void HandleState(){
		Date currentTime =  new Date();
		org.sttapc.medreminder.context.State currentState = StateProvider.getInstance().getState();
		switch(currentState){
		/* 
		 * If this State is triggered, it means that user has opened the case
		 * within the ACTIVE state. We assume the user is taking the medicine
		 * it changes state to IDLE
		 * Log info to a xml file.
		 */
		case ACTIVE:
				StateProvider.getInstance().setState(org.sttapc.medreminder.context.State.IDLE);
			try {
				logning.LogForMagneticHandler(currentTime, State.ACTIVE, adherence.CalculatePoints(currentTime));
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		/* 
		 * If Case is opened in IDLE state, it should not do nothing.
		 * Maybe Log it ?
		 */
		case IDLE:
			break;
		/*
		 * If the user opens in WARNING state, he is outside of the schedule for
		 * taking his medicine
		 * */
		case WARNING:
			StateProvider.getInstance().setState(org.sttapc.medreminder.context.State.IDLE);
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
}
