package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import java.util.Timer;
import java.util.TimerTask;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.common.Constants;

class Increment implements StopwatchState {//todo refactor
    /*The Increment state enables the user to set the timer to his desired value
    * up to a maximum of 99 seconds
    * The displayed time is measured in seconds
    * Guard conditions: if three seconds elapse since the last click the state machine is
    * forwarded to the decrement state
    * Otherwise the displayed time is incremented again*/

    public Increment(final StopwatchSMStateView sm) {
        this.sm = sm;
        clickCount = 1;//Since last transition incremented once
        tickCount = 0;
    }

    private final StopwatchSMStateView sm;
    private int clickCount;
    private int tickCount;

    @Override
    public void onStartStop() {//Increments displayed time measured in seconds
        clickCount = sm.actionAccessClickcount();
        if(clickCount < 98){//Preset max value of 99 secs
            sm.actionInc();
            tickCount = 0;//To know when is the last click in order to move on to next state or keep incrementing
        }
        else {//When it gets to 99, we make an automatic transition to decrement state
            sm.actionInc();
            clickCount++;
        }
    }

    @Override
    public void onTick() {
        //Timer t = new Timer();
        if(clickCount == 99){
            sm.toDecrementState();//Also triggers beep
            sm.actionPlayAlarm();
        }
        else {
                    tickCount++;
                    if (tickCount < 3) {//Guard Condition
                        sm.toIncrementState();
                    }
                    else/*if (tickCount == 3)*/ {//Guard Condition
                        sm.toDecrementState();//Includes beep
                        sm.actionPlayAlarm();
                    }
                }
    }
    public void setClickCount(int c){
        clickCount = c;
    }
    public void setTickCount(int t){
        tickCount = 0;
    }

    @Override
    public void updateView() {
        sm.updateUITimer();
    }

    @Override
    public int numOfClicks(){return clickCount;}

    public int numOfTicks(){return tickCount;}

    @Override
    public int getId() {//Serves adapter
        return R.string.INCREMENT;
    }//todo change return value
}
