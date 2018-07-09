package com.lunareclipse.bargy;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lunareclipse.bargy.ui.CultureActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class CultureActivityBasicTest {
    @Rule public ActivityTestRule<CultureActivity> mCultureActivity = new ActivityTestRule<>(CultureActivity.class);

    @Test
    public void cultureLayoutLoadSuccessful(){

        // Check that the first cultural objects loads and click
        onView(withId(R.id.rv_culture)).check(matches(isDisplayed()));
    }

}
