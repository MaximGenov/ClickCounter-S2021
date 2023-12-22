package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.common.Constants;

class Decrement implements StopwatchState {
    /*The Decrement state starts with a beep
    * It decrements the time displayed by the timer all the way to zero,
    * hence triggering another beep to conclude its task
    * UNLESS the start/stop button is pressed while decrementing which returns the state machine
    * back to the Stopped state*/

    public Decrement(final StopwatchSMStateView sm){//, int clicks) {
        this.sm = sm;
        //clickCount = clicks;
    }

    private final StopwatchSMStateView sm;
    private int clickCount;

    @Override
    public void onStartStop() {
        sm.actionStop();//Stops the clock from running
        sm.actionInit();//Resets clack and prompts to next transition: ALARM
    }



    @Override
    public void onTick() {
        clickCount = sm.actionAccessClickcount();
        if(clickCount > 0){
            //clickCount--;
            sm.actionDec();
        }
        else  {
            sm.toAlarmState();
            sm.actionPlayAlarm();
        }
    }


    @Override
    public void updateView() {
        //sm.updateUILaptime();
        sm.updateUITimer();

    }

    @Override
    public int numOfClicks(){return clickCount;}


    @Override
    public int getId() {//Serves adapter
        return R.string.DECREMENT;
    }
}
