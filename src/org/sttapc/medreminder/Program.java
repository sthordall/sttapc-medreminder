package org.sttapc.medreminder;

import java.io.IOException;

import org.sttapc.medreminder.util.Initializer;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.InputChangeEvent;
import com.phidgets.event.InputChangeListener;
import com.phidgets.event.SensorChangeEvent;
import com.phidgets.event.SensorChangeListener;

public class Program {

	public static InterfaceKitPhidget interfaceKitPhidget = null;

	public static AttachListener attachListener = new AttachListener() {
		@Override
		public void attached(AttachEvent arg0) {
			try {
				System.out.println("Interfacekit Attached!");
				System.out.println("Device name: "
						+ interfaceKitPhidget.getDeviceName());
				interfaceKitPhidget
						.addSensorChangeListener(sensorChangeListener);

				interfaceKitPhidget.setOutputState(1, false);
				interfaceKitPhidget.setOutputState(0, false);
				interfaceKitPhidget.addInputChangeListener(inputChangeListener);
			} catch (PhidgetException e) {
				HandleException(e);
			}
		}
	};

	public static SensorChangeListener sensorChangeListener = new SensorChangeListener() {

		@Override
		public void sensorChanged(SensorChangeEvent arg0) {
			System.out.println("SensorChanged");
			System.out.println("Sensor value : " + arg0.getValue());

		}
	};

	public static InputChangeListener inputChangeListener = new InputChangeListener() {

		@Override
		public void inputChanged(InputChangeEvent arg0) {
			System.out.println("InputChanged");

			System.out.println("Input value : " + arg0.getState()
					+ " on index: " + arg0.getIndex());
		}
	};

	public static void main(String[] args) {
		Initializer initializer = new Initializer();
		try {
			initializer.setupConfigurator("resources/configurator.json");
			initializer.persistConfigurator("resources/configurator.json");
		} catch (IOException e) {
			HandleException(e);
		}
	}

	public static void HandleException(Exception e) {
		System.out.println("An error occurred: " + e.getMessage());
		System.out.println("Stacktrace: ");
		e.printStackTrace();
	}
}