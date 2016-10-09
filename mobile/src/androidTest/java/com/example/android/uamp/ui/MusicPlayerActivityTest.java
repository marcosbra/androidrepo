package com.example.android.uamp.ui;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.android.uamp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MusicPlayerActivityTest {

    @Rule
    public ActivityTestRule<MusicPlayerActivity> mActivityTestRule = new ActivityTestRule<>(MusicPlayerActivity.class);

    @Test
    public void musicPlayerActivityTest() {
        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        withId(R.id.list_view),
                        0),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction relativeLayout2 = onView(
                allOf(childAtPosition(
                        withId(R.id.list_view),
                        0),
                        isDisplayed()));
        relativeLayout2.perform(click());

        ViewInteraction relativeLayout3 = onView(
                allOf(childAtPosition(
                        withId(R.id.list_view),
                        1),
                        isDisplayed()));
        relativeLayout3.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.play_pause), withContentDescription("play or pause"),
                        withParent(allOf(withId(R.id.fragment_playback_controls),
                                withParent(withId(R.id.controls_container)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.toolbar_container)))),
                        isDisplayed()));
        imageButton.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
