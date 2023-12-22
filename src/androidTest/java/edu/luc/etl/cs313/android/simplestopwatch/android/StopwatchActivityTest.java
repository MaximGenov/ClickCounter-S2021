package edu.luc.etl.cs313.android.simplestopwatch.android;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import edu.luc.etl.cs313.android.simplestopwatch.test.android.AbstractStopwatchActivityTest;

import static org.junit.Assert.assertNotNull;

/**
 * Concrete Android test subclass. Has to inherit from framework class and uses delegation to
 * concrete subclass of abstract test superclass.
 *
 * @author laufer
 * @see //http://developer.android.com/tools/testing/activity_testing.html
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class StopwatchActivityTest extends AbstractStopwatchActivityTest {

    @Rule
    public ActivityTestRule<StopwatchAdapter> activityRule =
            new ActivityTestRule<>(StopwatchAdapter.class);

    @Override
    protected StopwatchAdapter getActivity() {
        return activityRule.getActivity();
    }


}