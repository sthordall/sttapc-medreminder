package org.sttapc.medreminder;

import java.util.Date;

import org.sttapc.medreminder.handlers.MagneticHandler;
import org.sttapc.medreminder.util.Initializer;
import org.sttapc.medreminder.util.Schedule;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachEvent;

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
			
			Schedule schedule = new Schedule(lowerDate, upperDate);
			
			
			InterfaceKitPhidget interfaceKitPhidget = new InterfaceKitPhidget();
			System.out.println("Starting up....");
			magnetichandler = new MagneticHandler(interfaceKitPhidget, schedule);
			//magnetichandler.AttachMagneticHandler();
			interfaceKitPhidget.addInputChangeListener(magnetichandler.getInputChangeListener());
			interfaceKitPhidget.openAny();
			interfaceKitPhidget.waitForAttachment();
			
			
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
