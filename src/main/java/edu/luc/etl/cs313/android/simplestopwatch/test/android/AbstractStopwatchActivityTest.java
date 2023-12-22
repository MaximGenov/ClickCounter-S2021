package edu.luc.etl.cs313.android.simplestopwatch.test.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import android.widget.Button;
import android.widget.TextView;
import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;

//import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_MIN;

/**
 * Abstract GUI-level test superclass of several essential stopwatch scenarios.
 *
 * @author laufer
 *
 * TODO move this and the other tests to src/test once Android Studio supports
 * non-instrumentation unit tests properly.
 */
public abstract class AbstractStopwatchActivityTest {

    /**
     * Verifies that the activity under test can be launched.
     */
    @Test
    public void testActivityCheckTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", getActivity());
    }
    /**
     * Verifies the following scenario: time is 0.
     *
     * @throws Throwable
     */

    @Test
    public void testActivityScenarioInit() throws Throwable {
        getActivity().runOnUiThread(() -> assertEquals(0, getDisplayedValue()));
    }

    /**
     * Verifies the following scenario: time is 0, press start, wait 5+ seconds, expect time 5.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioRun() throws Throwable {
        getActivity().runOnUiThread(() -> {
            assertEquals(0, getDisplayedValue());
            assertTrue(getStartStopButton().performClick());//in increment state
            assertEquals(1, getDisplayedValue());
        });
        //Thread.sleep(5500); // <-- do not run this in the UI thread!
        //runUiThreadTasks();
        getActivity().runOnUiThread(() -> {
            for(int i = 0; i < 5; i++){
                getStartStopButton().performClick();
            }//= 1+5=6
            assertTrue(getStartStopButton().performClick());// Now = 7
            assertEquals(7, getDisplayedValue());
        });

        Thread.sleep(5500); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        //it takes 3 ticks to start decrementing so time = (round) 7.0 -(5.5-3.0)= 5 secs
        getActivity().runOnUiThread(()->{//DECREMENTS
            //assertTrue(getStartStopButton().performClick());
            assertEquals(5, getDisplayedValue());
            assertTrue(getStartStopButton().performClick());//Back to 0 an stopped
        });
        getActivity().runOnUiThread(()->{
            assertTrue(getStartStopButton().performClick());//=1
            for(int i = 1; i < 99; i++){
                getStartStopButton().performClick();
            }// time = 99
            assertEquals(99, getDisplayedValue());
        });
        Thread.sleep(5500); // <-- do not run this in the UI thread!
        runUiThreadTasks();

        getActivity().runOnUiThread(()->{
            assertEquals(95,getDisplayedValue());
            assertTrue(getStartStopButton().performClick());
            assertEquals(0, getDisplayedValue());
        });
    }

    /**
     * Verifies the following scenario: time is 0, press start, wait 5+ seconds,
     * expect time 5, press lap, wait 4 seconds, expect time 5, press start,
     * expect time 5, press lap, expect time 9, press lap, expect time 0.
     *
     * @throws Throwable
     */

    // auxiliary methods for easy access to UI widgets

    protected abstract StopwatchAdapter getActivity();

    protected int tvToInt(final TextView t) {
        return Integer.parseInt(t.getText().toString().trim());
    }

    protected int getDisplayedValue() {
        final TextView t = getActivity().findViewById(R.id.time);
        return tvToInt(t);
    }

    protected Button getStartStopButton() {
        return (Button) getActivity().findViewById(R.id.startStop);
    }

    /**
     * Explicitly runs tasks scheduled to run on the UI thread in case this is required
     * by the testing framework, e.g., Robolectric.
     */
    protected void runUiThreadTasks() { }
}
