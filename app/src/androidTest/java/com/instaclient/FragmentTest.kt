package com.instaclient

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.SmallTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.instaclient.flow.main_activity.MainActivity
import com.instaclient.flow.post_detail.TagResultFragment
import com.instaclient.flow.tag_search.TagSearchFragment
import junit.framework.Assert
import junit.framework.Assert.assertTrue
import kotlinx.android.synthetic.main.fragment_search_tag.*
import kotlinx.android.synthetic.main.fragment_tag_detail.*
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class FragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSearch() {
        val activity = activityRule.activity

        val dialog = activity.getWarningDialog()
        Assert.assertTrue(dialog.isShowing)

        if (dialog.isShowing) {
            dialog.dismiss()
        }

        assertTrue(activity.supportFragmentManager.fragments.size > 0)
        val fragment = activity.supportFragmentManager.fragments[0] as TagSearchFragment
        assertThat(fragment.tagEditText, notNullValue())
        onView(withId(R.id.tagEditText)).perform(click(), typeText("travels"))
                .check(matches(withText("travels")))
        Thread.sleep(2000)
        onView(withId(R.id.recyclerViewTags))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun testDetail() {
        val activity = activityRule.activity

        val dialog = activity.getWarningDialog()
        Assert.assertTrue(dialog.isShowing)

        if (dialog.isShowing) {
            dialog.dismiss()
        }

        onView(withId(R.id.tagEditText)).perform(click(), typeText(TO_TYPE))
                .check(matches(withText(TO_TYPE)))
        Thread.sleep(4000)
        onView(withId(R.id.recyclerViewTags))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(4000)
        assertTrue(activity.supportFragmentManager.fragments.size > 0)
        val fragment = activity.supportFragmentManager.fragments[0] as TagResultFragment
        assertThat(fragment.recyclerViewPosts, notNullValue())
        assertThat(fragment.toolbar_detail.title.toString(), containsString(TO_TYPE))
    }

    @Test
    fun testBackButton() {
        val activity = activityRule.activity

        val dialog = activity.getWarningDialog()
        Assert.assertTrue(dialog.isShowing)

        if (dialog.isShowing) {
            dialog.dismiss()
        }

        onView(withId(R.id.tagEditText)).perform(click(), typeText(TO_TYPE))
                .check(matches(withText(TO_TYPE)))
        Thread.sleep(4000)
        onView(withId(R.id.recyclerViewTags))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(4000)
        assertTrue(activity.supportFragmentManager.fragments.size > 0)
        pressBack()
        assertTrue(activity.supportFragmentManager.fragments.size > 0)
    }

    var TO_TYPE = "travels"
}