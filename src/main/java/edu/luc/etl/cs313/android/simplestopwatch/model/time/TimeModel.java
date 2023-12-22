package edu.luc.etl.cs313.android.simplestopwatch.model.time;

/**
 * The passive data model of the stopwatch.
 * It does not emit any events.
 *
 * @author laufer
 */
public interface TimeModel {
    void resetTimer();
    void incTimer();
    void decTimer();
    int getRemainingTime();
    void editTimer(int t);
}
