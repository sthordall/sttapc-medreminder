package org.sttapc.medreminder.util;

import org.sttapc.medreminder.handlers.MotionHandler;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;

//TODO: Initialize system, setup handlers, configurator etc.
public class Initializer {
	private InterfaceKitPhidget interfaceKitPhidget;
	private MotionHandler motionHandler;
	private Configurator configurator;

	/**
	 * All initialization associated with the Configurator
	 */
	public void setupConfigurator() {
		configurator = new Configurator();
	}

	/**
	 * All initialization associated with the InterfaceKit
	 * 
	 * @throws PhidgetException
	 */
	public void setupInterfaceKit() throws PhidgetException {
		interfaceKitPhidget = new InterfaceKitPhidget();
	}

	/**
	 * All initialization associated with the MotionSensor
	 * 
	 * @throws PhidgetException
	 */
	public void setupMotionSensor() throws PhidgetException {
		if (interfaceKitPhidget == null) {
			setupInterfaceKit();
		}
		if (configurator == null) {
			setupConfigurator();
		}

		configurator.setupMotionHandler();
		motionHandler = new MotionHandler(configurator);
		interfaceKitPhidget.addSensorChangeListener(motionHandler
				.getSensorChangeListener());

	}

	/**
	 * Setups entire system, via use of configurator. If this went well, return
	 * true, else return false.
	 */
	public boolean InitializeSystem() {
		configurator = new Configurator();
		// TODO: setup entire system, via use of configurator

		return true;
	}
}
