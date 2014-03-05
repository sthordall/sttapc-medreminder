package org.sttapc.medreminder;

import java.util.Date;

import org.sttapc.medreminder.context.Logning;
import org.sttapc.medreminder.context.State;
import org.sttapc.medreminder.handlers.MagneticHandler;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;

public class TestProgram {
	static MagneticHandler magnetichandler;
	Date lowerDate = new Date();
	Date upperDate = new Date();

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Date lowerDate = new Date();
		Date upperDate = new Date();
		try {
			lowerDate.setHours(10);
			lowerDate.setMinutes(0);
			upperDate.setHours(11);
			upperDate.setMinutes(0);
			
//			InterfaceKitPhidget interfaceKitPhidget = new InterfaceKitPhidget();
//			System.out.println("Starting up....");
//			magnetichandler = new MagneticHandler(interfaceKitPhidget);
//			// magnetichandler.AttachMagneticHandler();
//			interfaceKitPhidget.addInputChangeListener(magnetichandler
//					.getInputChangeListener());
//			interfaceKitPhidget.openAny();
//			interfaceKitPhidget.waitForAttachment();
			
			Logning logning = new Logning();
			
			logning.LogForMagneticHandler(lowerDate, State.ACTIVE, 3);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
