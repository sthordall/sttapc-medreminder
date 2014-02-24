package org.sttapc.medreminder.context;

public class StateProvider {
	private static StateProvider instance = null;

	private State state;

	private StateProvider() {
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public static StateProvider getInstance() {
		if (instance == null) {
			instance = new StateProvider();
		}
		return instance;
	}
}
