package org.sttapc.medreminder.context;

public class StateProvider {
	private static StateProvider instance = null;

	private State state;
	private boolean IsCaseClosed = false;
	

	public boolean isCaseClosed() {
		return IsCaseClosed;
	}

	public void setIsCaseClosed(boolean IsCaseClosed) {
		this.IsCaseClosed = IsCaseClosed;
	}

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
