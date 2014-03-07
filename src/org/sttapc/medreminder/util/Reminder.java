package org.sttapc.medreminder.util;

import java.io.IOException;

public class Reminder {

	private MakeSound makeSound;
	private String filePath;

	public Reminder(Configurator configurator) {
		//makeSound = new MakeSound();
		filePath = configurator.getFilePath();
		System.out.println(filePath);
	}

	public void NearbyActiveReminder() throws IOException {
		System.out.println("REMINDER: Patient Nearby - Take medication");
		if(filePath == null){
			filePath = "resources/nearbyActiveSound.sh";
		}
		Process p = new ProcessBuilder(filePath).start();
		//makeSound.playSound("resources/nearbyActiveSound.wav");
	}

	public void NearbyWarningReminder() throws IOException {
		System.out
				.println("REMINDER: Patient Nearby - Warning, take medication now!");
		if(filePath == null){
			filePath = "resources/nearbyActiveSound.sh";
		}
		Process p = new ProcessBuilder(filePath).start();
		//makeSound.playSound("resources/nearbyActiveSound.wav");
	}
}
