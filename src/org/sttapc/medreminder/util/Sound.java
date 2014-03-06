package org.sttapc.medreminder.util;

import java.util.Timer;
import java.util.TimerTask;

import com.phidgets.InterfaceKitPhidget;

public class Sound {
	InterfaceKitPhidget interfaceKitPhidget;
	Timer pwmTimer;
	int outputPin;
	boolean outputState = true;

	public Sound(InterfaceKitPhidget interfaceKit, int outPin) {
		interfaceKitPhidget = interfaceKit;
		outputPin = outPin;
		pwmTimer = new Timer();
	}

	public void PlaySound(int freq) {
		pwmTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					outputState = !outputState;
					interfaceKitPhidget.setOutputState(outputPin, outputState);
				} catch (Exception e) {
					System.out.println("An error occured: " + e.getMessage());
					e.printStackTrace();
				}

			}
		}, 1 / freq);
	}

	public void StopSound() {
		pwmTimer.cancel();
	}
}
