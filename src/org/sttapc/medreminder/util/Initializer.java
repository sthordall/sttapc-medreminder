package org.sttapc.medreminder.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.sttapc.medreminder.handlers.MotionHandler;

import com.google.gson.Gson;
import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;

//TODO: Initialize system, setup handlers, configurator etc.
public class Initializer {
	private InterfaceKitPhidget interfaceKitPhidget;
	private MotionHandler motionHandler;
	private Configurator configurator;
	private String jsonConfigPath = "resources/configurator.json";

	public Configurator getConfigurator() {
		return configurator;
	}

	/**
	 * All setup associated with the Configurator
	 * 
	 * @throws IOException
	 */
	public void setupConfigurator(String configuratorPath) throws IOException {
		Gson gson = new Gson();

		byte[] encoded = Files.readAllBytes(Paths.get(configuratorPath));
		String jsonString = StandardCharsets.UTF_8.decode(
				ByteBuffer.wrap(encoded)).toString();

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

	/**
	 * Setups entire system. If this went well, return true, else return false.
	 */
	public boolean InitializeSystem() {
		try {
			setupConfigurator(jsonConfigPath);
			setupInterfaceKit();
			setupMotionSensor();
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			return false;
		}

		return true;
	}
}
