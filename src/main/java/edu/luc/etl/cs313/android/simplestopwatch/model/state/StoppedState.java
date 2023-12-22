package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class StoppedState implements StopwatchState {//Default state

    /*When the application is launched, the state of the timer is defaulted to this state (stopped)
    * This state does not include tick events
    * However, once we click on the multi-function button (i.e. Start/Stop)
    * the state machine is forwarded to Increment (the next state) while still incrementing the displayed time once*/

    public StoppedState(final StopwatchSMStateView sm) {//Constructor
        this.sm = sm;
        clickCount = 0;
        tickCount = 0;
    }

    private final StopwatchSMStateView sm;
    protected int tickCount;
    private int clickCount;

    @Override
    public void onStartStop() {//Multi-function button
        sm.actionStart();
        clickCount = 0;
        tickCount = 0;
        sm.actionEdit();
        sm.toIncrementState();//Forwards state machine to the next state
        if(sm.actionAccessClickcount() > 0) {
            //Do nothing
        }
        else {
            sm.actionInc();//Increments once
        }
    }


    @Override
    public void onTick() {//No ticks here
        throw new UnsupportedOperationException("onTick");
    }//No ticks in stopped state

    @Override
    public void updateView() {//Timer object is kept posted
        sm.updateUITimer();
    }

    @Override
    public int numOfClicks(){return clickCount;}//No click event at Stopped state

    public int numOfTicks(){return tickCount;}//No ticks at this point either

    @Override
    public int getId() {//Serves adapter
        return R.string.STOPPED;
    }//TODO change return value
}
