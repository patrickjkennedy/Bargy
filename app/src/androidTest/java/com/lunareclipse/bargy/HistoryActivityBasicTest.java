package com.lunareclipse.bargy;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lunareclipse.bargy.ui.HistoryActivity;
import com.lunareclipse.bargy.ui.HomeActivity;
import com.lunareclipse.bargy.ui.MenuActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class HistoryActivityBasicTest {
    @Rule public ActivityTestRule<HistoryActivity> mHistoryActivity = new ActivityTestRule<>(HistoryActivity.class);

    @Test
    public void checkLayout(){
        // Check to see that the expected headings have loaded
        onView(withId(R.id.tv_summary_heading)).check(matches(withText("Summary")));
        onView(withId(R.id.tv_detail_heading)).check(matches(withText("Detail")));
    }

}
