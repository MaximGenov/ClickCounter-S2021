package edu.luc.etl.cs313.android.simplestopwatch.test.model.time;

//import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_HOUR;
//import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_MIN;
import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_TICK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * Testcase superclass for the time model abstraction.
 * This is a simple unit test of an object without dependencies.
 *
 * @author laufer
 * @see //http://xunitpatterns.com/Testcase%20Superclass.html
 */
public abstract class AbstractTimeModelTest {

    private TimeModel model;

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final TimeModel model) {
        this.model = model;
    }

    /**
     * Verifies that runtime and laptime are initially 0 or less.
     */
    @Test
    public void testPreconditions() {
        assertEquals(0, model.getRemainingTime());
        //assertTrue(model.getLaptime() <= 0);
    }

    /**
     * Verifies that runtime is incremented correctly.
     */
    @Test
    public void testIncrementRuntimeOne() {
        final int rt = model.getRemainingTime();
        //final int lt = model.getLaptime();
        model.incTimer();
        assertEquals((rt + SEC_PER_TICK), model.getRemainingTime());
        //assertEquals(lt, model.getLaptime());
    }

    /**
     * Verifies that runtime turns over correctly.
     */
    @Test
    public void testIncrementRuntimeMany() {

        //final int lt = model.getLaptime();
        for (int i = 0; i < 99; i ++) {
            model.incTimer();
        }
        final int rt = model.getRemainingTime();
        assertEquals(rt, model.getRemainingTime());
    }

}
