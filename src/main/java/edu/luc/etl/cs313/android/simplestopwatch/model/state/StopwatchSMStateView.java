package edu.luc.etl.cs313.android.simplestopwatch.model.state;


/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface StopwatchSMStateView {

    // transitions
    /*void toRunningState();
    void toStoppedState();
    void toLapRunningState();
    void toLapStoppedState();*/

    //Timer Transitions
    void toIncrementState();
    void toDecrementState();
    void toAlarmState();
    void toStoppedState();

    // actions
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    void actionInc();
    void actionDec();
    void actionUpdateView();
    void actionPlayAlarm();
    int actionAccessClickcount();
    void actionEdit();

    // state-dependent UI updates
    void updateUITimer();
}
