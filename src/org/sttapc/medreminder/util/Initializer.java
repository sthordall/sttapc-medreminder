package org.sttapc.medreminder.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.sttapc.medreminder.context.LifeCycle;
import org.sttapc.medreminder.handlers.MagneticHandler;
import org.sttapc.medreminder.handlers.MotionHandler;

import com.google.gson.Gson;
import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;

//TODO: Initialize system, setup handlers, configurator etc.
public class Initializer {
	private InterfaceKitPhidget interfaceKitPhidget;
	private MotionHandler motionHandler;
	private MagneticHandler magneticHandler;
	private Configurator configurator;
	private String jsonConfigPath = "resources/configurator.json";
	private LifeCycle lifeCycle;

	public Configurator getConfigurator() {
		return configurator;
	}

	public void setConfigurator(Configurator conf) {
		configurator = conf;
	}

	/**
	 * All setup associated with the Configurator
	 * 
	 * @throws IOException
	 */
	public void setupConfigurator(String configuratorPath) throws IOException {
		Gson gson = new Gson();
		String jsonString = "";
		try {
			Scanner scanner = new Scanner(new File(
					"resources/configurator.json"));
			while (scanner.hasNext()) {
				jsonString += scanner.next();
				jsonString += " ";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		configurator = gson.fromJson(jsonString, Configurator.class);
	}

	public void persistConfigurator(String configuratorPath) {
		Gson gson = new Gson();
		String jsonConfigString = gson.toJson(this.configurator);
		try {
			PrintWriter printWriter = new PrintWriter(configuratorPath, "UTF-8");
			printWriter.write(jsonConfigString);
			System.out.println("Configurator persisted: \n" + jsonConfigString);
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * @throws IOException
	 */
	public void setupMotionSensor() throws PhidgetException, IOException {
		if (interfaceKitPhidget == null) {
			setupInterfaceKit();
		}
		if (configurator == null) {
			setupConfigurator(jsonConfigPath);
		}

		motionHandler = new MotionHandler(configurator);
		interfaceKitPhidget.addSensorChangeListener(motionHandler
				.getSensorChangeListener());
	}

	public void setupMagneticSensor() throws PhidgetException, IOException {
		if (interfaceKitPhidget == null) {
			setupInterfaceKit();
		}
		if (configurator == null) {
			setupConfigurator(jsonConfigPath);
		}

		magneticHandler = new MagneticHandler(interfaceKitPhidget, configurator);
		// magneticHandler.AttachMagneticHandler();
		interfaceKitPhidget.addInputChangeListener(magneticHandler
				.getInputChangeListener());
	}

	/**
	 * Setups entire system. If this went well, return true, else return false.
	 */
	public boolean InitializeSystem() {
		try {
			setupConfigurator(jsonConfigPath);
			setupInterfaceKit();
			setupMotionSensor();
			setupMagneticSensor();
			lifeCycle = new LifeCycle(configurator);
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public void StartSystem() {
		lifeCycle.startLifeCycle();
		try {
			interfaceKitPhidget.openAny();
		} catch (PhidgetException e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
