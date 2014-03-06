package org.sttapc.medreminder.util;

public class Reminder {

	private MakeSound makeSound;

	public Reminder() {
		makeSound = new MakeSound();
	}

	public void NearbyActiveReminder() {
		System.out.println("REMINDER: Patient Nearby - Take medication");
		makeSound.playSound("resources/nearbyActiveSound.wav");
	}

	public void NearbyWarningReminder() {
		System.out
				.println("REMINDER: Patient Nearby - Warning, take medication now!");
		makeSound.playSound("resources/nearbyActiveSound.wav");
	}

}
