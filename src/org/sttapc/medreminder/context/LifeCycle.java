package org.sttapc.medreminder.context;

import java.util.Timer;
import java.util.TimerTask;

import org.sttapc.medreminder.util.Configurator;

public class LifeCycle {

	private static long HOURS24 = 1000 * 60 * 60 * 24;

	private Schedule schedule;
	private StateProvider stateProvider;

	private Timer startActiveStateTimer;
	private TimerTask startActiveStateTimerTask;

	private Timer startWarningStateTimer;
	private TimerTask startWarningStateTimerTask;

	private Timer startIdleStateTimer;
	private TimerTask startIdleStateTimerTask;

	// TODO: Add handling of scenario: Pill taken in idle, before activation of
	// active => Dont turn to active state.

	public LifeCycle(Configurator configurator) {
		schedule = configurator.getSchedule();
		stateProvider = configurator.getStateProvider();
	}

	/**
	 * Sets up all timertasks and timers. Timers execution time is set according
	 * to the supplied schedule
	 */
	public void startLifeCycle() {
		startIdleStateTimerTask = new TimerTask() {
			@Override
			public void run() {
				stateProvider.setState(State.IDLE);
			}
		};

		startActiveStateTimerTask = new TimerTask() {
			@Override
			public void run() {
				if (stateProvider.getState() == State.IDLE) {
					stateProvider.setState(State.ACTIVE);
				}
			}
		};

		startWarningStateTimerTask = new TimerTask() {
			@Override
			public void run() {
				if (stateProvider.getState() == State.ACTIVE) {
					stateProvider.setState(State.WARNING);
				}
			}
		};

		startIdleStateTimer = new Timer();
		startIdleStateTimer.schedule(startIdleStateTimerTask,
				schedule.getStartIdleDate(), HOURS24);

		startActiveStateTimer = new Timer();
		startActiveStateTimer.schedule(startActiveStateTimerTask,
				schedule.getStartActiveDate(), HOURS24);

		startWarningStateTimer = new Timer();
		startWarningStateTimer.schedule(startWarningStateTimerTask,
				schedule.getStartWarningDate(), HOURS24);
	}

	/**
	 * Cancels all timers.
	 */
	public void StopLifeCycle() {
		startIdleStateTimer.cancel();
		startActiveStateTimer.cancel();
		startWarningStateTimer.cancel();
	}
}
