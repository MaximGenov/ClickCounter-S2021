package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class Alarm implements StopwatchState {
    /*This is the last state before concluding the timer's cycle
    * An alarm (not so elegant) is triggered indefinitely until
    * the start/stop button is pressed once to prompt the state machine back
    * to its original state (stopped) whereby the alarm stops ringing*/

    public Alarm(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;
    private int clickCount;

    @Override
    public void onStartStop() {//Stops the sound notification from the alarm
        sm.actionStop();//stops clock
        sm.actionInit();//Back to stopped state
    }


    @Override
    public void onTick() {
        /*throw new UnsupportedOperationException("onTick");*/
        sm.actionPlayAlarm();
    }

    @Override
    public void updateView() {
        sm.updateUITimer();
    }

    @Override
    public int numOfClicks(){return clickCount;}//Decremented to 0


    @Override
    public int getId() {//TODO change returned id for all states
        return R.string.ALARM;
    }//Alarms the adapter
}
