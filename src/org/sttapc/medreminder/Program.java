package org.sttapc.medreminder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		try {
			Scanner scanner = new Scanner(new File(
					"resources/configurator.json"));
			while (scanner.hasNext()) {
				System.out.print(scanner.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Reminder reminder = new Reminder();
		// reminder.NearbyActiveReminder();
		// Configurator configurator = new Configurator();
		// configurator.setSchedule(new Schedule(new Date()));
		// configurator.setLogning(new Logning());
		// Initializer initializer = new Initializer();
		// initializer.setConfigurator(configurator);
		// initializer.persistConfigurator("resources/configurator1.json");

		//
		// try {
		// final InterfaceKitPhidget interfaceKitPhidget = new
		// InterfaceKitPhidget();
		// interfaceKitPhidget.addAttachListener(new AttachListener() {
		//
		// @Override
		// public void attached(AttachEvent arg0) {
		// try {
		// System.out.println("InterfaceKit Attached");
		// Sound sound = new Sound(interfaceKitPhidget, 0);
		// sound.PlaySound(440);
		// } catch (Exception e) {
		// HandleException(e);
		// }
		//
		// }
		// });
		// interfaceKitPhidget.openAny();
		// while (true) {
		//
		// }
		// } catch (Exception e) {
		// HandleException(e);
		// }
	}

	public static void HandleException(Exception e) {
		System.out.println("An error occurred: " + e.getMessage());
		System.out.println("Stacktrace: ");
		e.printStackTrace();
	}
}