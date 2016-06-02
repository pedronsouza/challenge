package br.pedronsouza.desafio.bankfacil.activities;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.concretesolutions.requestmatcher.InstrumentedTestRequestMatcherRule;
import br.com.concretesolutions.requestmatcher.RequestMatcher;
import br.com.concretesolutions.requestmatcher.RequestMatcherRule;
import br.pedronsouza.desafio.bankfacil.R;
import br.pedronsouza.desafio.bankfacil.ui.activities.MainActivity_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity_> rule = new ActivityTestRule<>(MainActivity_.class);

    @Rule
    public RequestMatcherRule serverRule = new InstrumentedTestRequestMatcherRule(InstrumentationRegistry.getContext());

    @Before
    public void setUp() {
        serverRule.url("/");
    }

    @Test
    public void assertApiIntegration() {
        rule.launchActivity(new Intent());
        SystemClock.sleep(1000);
        serverRule
                .enqueue(200, "users.json")
                .assertPathIs("/")
                .assertHasQuery("results", "70")
                .assertMethodIs(RequestMatcher.GET);
    }

    @Test
    public void toolbarShouldBePresent() {
        rule.launchActivity(new Intent());
        SystemClock.sleep(200);
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
    }

    @Test
    public void progressBarMustBeDisplayedOnLaunch() {
        rule.launchActivity(new Intent());
        SystemClock.sleep(100);
        onView(withId(R.id.proress_indic)).check(matches(isDisplayed()));
    }
}
