package com.lunareclipse.bargy;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lunareclipse.bargy.ui.HistoryActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class HistoryActivityBasicTest {
    @Rule public ActivityTestRule<HistoryActivity> mHistoryActivity = new ActivityTestRule<>(HistoryActivity.class);

    @Test
    public void checkLayout(){
        // Check that the new screen loads
        onView(withId(R.id.rv_history)).check(matches(isDisplayed()));
    }

}
