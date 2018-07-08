package com.lunareclipse.bargy;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
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
public class MenuActivityBasicTest {
    @Rule public ActivityTestRule<MenuActivity> mMenuActivity = new ActivityTestRule<>(MenuActivity.class);

    @Test
    public void navigateToGlossaryActivity(){
        // Find the correct card and click
        onView(withId(R.id.cv_glossary)).perform(click());

        // Check that the new screen loads
        onView(withId(R.id.tv_search_heading)).check(matches(withText("Search English to Yola")));
    }

    @Test
    public void navigateToHistoryActivity(){
        // Find the correct card and click
        onView(withId(R.id.cv_history)).perform(scrollTo()).perform(click());

        // Check that the new screen loads
        onView(withId(R.id.tv_summary_heading)).check(matches(withText("Summary")));
    }

    @Test
    public void navigateToCultureActivity(){
        // Find the correct card and click
        onView(withId(R.id.cv_culture)).perform(scrollTo()).perform(click());

        // Check that the new screen loads
        onView(withId(R.id.rv_culture)).check(matches(isDisplayed()));
    }
}
