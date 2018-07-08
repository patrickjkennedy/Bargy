package com.lunareclipse.bargy;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.lunareclipse.bargy.ui.HomeActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class HomeActivityBasicTest {
    @Rule public ActivityTestRule<HomeActivity> mHomeActivity = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void checkLanguagesLoad(){
        // Check that the Yola card loads as expected
        onView(withId(R.id.rv_master_list))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("Yola")),
                        click()));
    }

    @Test
    public void navigateToMenuActivity(){
        // Find the view and perform an action
        onView(withId(R.id.rv_master_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Check that the titles of the cardviews are correct
        onView(withId(R.id.tv_glossary)).check(matches(withText("Glossary")));
        onView(withId(R.id.tv_history)).check(matches(withText("History")));
        onView(withId(R.id.tv_culture)).check(matches(withText("Culture")));
    }
}
