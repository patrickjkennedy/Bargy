package com.lunareclipse.bargy;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lunareclipse.bargy.ui.CultureActivity;
import com.lunareclipse.bargy.ui.GlossaryActivity;
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
public class GlossaryActivityBasicTest {
    @Rule public ActivityTestRule<GlossaryActivity> mGlossaryActivity = new ActivityTestRule<>(GlossaryActivity.class);

    @Test
    public void glossaryLayoutLoadSuccessful(){

        onView(withId(R.id.tv_search_heading)).check(matches(withText("Search English to Yola")));
        onView(withId(R.id.ib_search)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_glossary)).check(matches(isDisplayed()));
    }

}