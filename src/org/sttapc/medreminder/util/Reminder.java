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
			filePath = "/usr/userapps/MedReminder/resources/nearbyActiveSound1.sh";
		//Local TEST
		//filePath = "resources/nearbyActiveSound.sh";
		try {
			System.out.println(filePath);
			String[] cmd = new String[]{"/bin/sh", filePath};
			Process pr = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//makeSound.playSound("resources/nearbyActiveSound.wav");
	}

	public void NearbyWarningReminder(int countOnSoundVolume) throws IOException {
		System.out
				.println("REMINDER: Patient Nearby - Warning, take medication now!");
			filePath = "/usr/userapps/MedReminder/resources/nearbyActiveSound" + countOnSoundVolume + ".sh";
		//Local TEST
		//filePath = "resources/nearbyActiveSound.sh";
		try {
			System.out.println(filePath);
			String[] cmd = new String[]{"/bin/sh", filePath};
			Process pr = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//makeSound.playSound("resources/nearbyActiveSound.wav");
		//makeSound.playSound("resources/nearbyActiveSound.wav");
	}
}
