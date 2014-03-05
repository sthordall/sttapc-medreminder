package org.sttapc.medreminder.util;

public class Reminder {

	// private MakeSound makeSound;
	// private String nearbyActiveSoundPath = "resources/nearbyActiveSound.mp3";
	// private String nearbyWarningSoundPath =
	// "resources/nearbyWarningSound.mp3";

	public Reminder() {
		// makeSound = new MakeSound();
	}

	public void NearbyActiveReminder() {
		System.out.println("REMINDER: Patient Nearby - Take medication");
		// makeSound.playSound(nearbyActiveSoundPath);
	}

	public void NearbyWarningReminder() {
		System.out
				.println("REMINDER: Patient Nearby - Warning, take medication now!");
		// makeSound.playSound(nearbyWarningSoundPath);
	}

}
