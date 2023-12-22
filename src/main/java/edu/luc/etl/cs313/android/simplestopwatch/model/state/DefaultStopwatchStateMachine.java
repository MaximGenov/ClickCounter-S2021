package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;
import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * An implementation of the state machine for the stopwatch.
 *
 * @author laufer
 */
public class DefaultStopwatchStateMachine implements StopwatchStateMachine {

    public DefaultStopwatchStateMachine(final TimeModel timeModel, final ClockModel clockModel) {
        this.timeModel = timeModel;
        this.clockModel = clockModel;
    }

    private final TimeModel timeModel;

    private final ClockModel clockModel;

    /**
     * The internal state of this adapter component. Required for the State pattern.
     */
    private StopwatchState state;

    protected void setState(final StopwatchState state) {
        this.state = state;
        uiUpdateListener.updateState(state.getId());
    }

    private StopwatchUIUpdateListener uiUpdateListener;

    @Override
    public void setUIUpdateListener(final StopwatchUIUpdateListener uiUpdateListener) {
        this.uiUpdateListener = uiUpdateListener;
    }
    @Override
    public void updateUITimer(){
        uiUpdateListener.updateTime(timeModel.getRemainingTime());
    }

    // forward event uiUpdateListener methods to the current state
    // these must be synchronized because events can come from the
    // UI thread or the timer thread
    //@Override public synchronized void onTextView(){state.onTextView();}
    @Override public synchronized void onStartStop() { state.onStartStop(); }
    @Override public synchronized void onTick()      { state.onTick(); }


    // known states
    private final StopwatchState STOPPED = new StoppedState(this);
    private final StopwatchState INC  = new Increment(this);
    private final StopwatchState DEC = new Decrement(this);//, /*state.numOfClicks()*//*((Increment)INC).numOfClicks()*/);
    private final StopwatchState ALARM = new Alarm(this);


    //Transitions
    @Override
    public void toIncrementState() {
        setState(INC);
    }

    @Override
    public void toDecrementState() {
        //int clicks = state.numOfClicks();
        setState(DEC);
        //((Decrement) state).setClickCount(clicks);

    }

    @Override
    public void toAlarmState() {
        setState(ALARM);
        //todo implement beep sound
        //actionStop();
        //actionPlayAlarm();
    }

    @Override
    public void toStoppedState() {

        setState(STOPPED);
        //((Increment) INC).setClickCount(1);
    }

    // actions

    @Override public void actionInit(){toStoppedState(); actionReset();}//Default state Transition
    @Override public void actionReset(){timeModel.resetTimer(); actionUpdateView();}
    @Override public void actionStart(){clockModel.start();}
    @Override public void actionStop(){clockModel.stop();}
    @Override public void actionInc(){timeModel.incTimer(); actionUpdateView();}
    @Override public void actionDec(){timeModel.decTimer(); actionUpdateView();}
    @Override public void actionUpdateView(){state.updateView();}
    @Override public void actionPlayAlarm(){((StopwatchAdapter) uiUpdateListener).playSoundNotification();}
    @Override public int actionAccessClickcount(){return timeModel.getRemainingTime();}
    @Override public void actionEdit(){timeModel.editTimer(((StopwatchAdapter) uiUpdateListener).getTextView());/**/;}

}
