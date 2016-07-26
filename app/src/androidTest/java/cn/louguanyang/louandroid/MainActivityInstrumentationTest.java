package cn.louguanyang.louandroid;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cn.louguanyang.louandroid.activitys.MainActivity;


/**
 * Created by louguanyang on 16/3/30.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void testUI() {
//        onView(withId(R.id.etUserName)).perform(typeText("user001"),closeSoftKeyboard());
//        onView(withId(R.id.etPassword)).perform(typeText("123456"),closeSoftKeyboard());
//        onView(withId(R.id.btnLogin)).perform(click());
    }

}
