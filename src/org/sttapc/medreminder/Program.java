package org.sttapc.medreminder;

import org.sttapc.medreminder.util.Reminder;

public class Program {

	public static void main(String[] args) {
		Reminder reminder = new Reminder();
		try {
			reminder.NearbyActiveReminder();
		} catch (Exception e) {
			HandleException(e);
		}
	}

	public static void HandleException(Exception e) {
		System.out.println("An error occurred: " + e.getMessage());
		System.out.println("Stacktrace: ");
		e.printStackTrace();
	}
}