package org.sttapc.medreminder;

import org.sttapc.medreminder.util.Initializer;

public class TestProgram {

	public static void main(String[] args) {
		Initializer initializer = new Initializer();
		initializer.InitializeSystem();
		initializer.StartSystem();

	}
}
