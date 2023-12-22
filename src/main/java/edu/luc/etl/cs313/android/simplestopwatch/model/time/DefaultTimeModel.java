package edu.luc.etl.cs313.android.simplestopwatch.model.time;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.*;

/**
 * An implementation of the stopwatch data model.
 */
public class DefaultTimeModel implements TimeModel {
    //Controls the time that is to be displayed to the various states

    private int runningTime = 0;

    @Override
    public void resetTimer() {
        runningTime = 0;
    }

    @Override
    public void incTimer() {//TODO Use clicks to increment
        runningTime = runningTime + SEC_PER_TICK;
    }
    @Override
    public void decTimer(){
        runningTime = runningTime - SEC_PER_TICK;
    }

    @Override
    public int getRemainingTime() {
        return runningTime;
    }
    @Override
    public void editTimer(int t){runningTime = t;}


}