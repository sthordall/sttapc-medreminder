package org.sttapc.medreminder;

import org.sttapc.medreminder.handlers.MagneticHandler;
import org.sttapc.medreminder.util.Initializer;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachEvent;

public class TestProgram {
	static MagneticHandler magnetichandler;
	
	
	public static void main(String[] args) {
		
		try {
			Initializer init = new Initializer();
			
			Initializer.getCurrentTime();
			InterfaceKitPhidget interfaceKitPhidget = new InterfaceKitPhidget();
			System.out.println("Starting up....");
			magnetichandler = new MagneticHandler(interfaceKitPhidget);
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
