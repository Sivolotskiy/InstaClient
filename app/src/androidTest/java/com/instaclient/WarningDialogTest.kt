package com.instaclient
import android.app.AlertDialog
import android.support.test.annotation.UiThreadTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.filters.LargeTest

import com.instaclient.flow.main_activity.MainActivity

import android.support.test.espresso.matcher.ViewMatchers.*
import junit.framework.Assert.*
import org.hamcrest.CoreMatchers.*

@RunWith(AndroidJUnit4::class)
@LargeTest
class WarningDialogTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @UiThreadTest
    fun dialog_sameActivity() {
        val activity = activityRule.activity

        val dialog = activity.getWarningDialog()
        assertTrue(dialog.isShowing)

        if (dialog.isShowing) {
            val button =  dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            assertThat(button, not(nullValue()))
            assertThat(button.text.toString(), equalTo("OK"))
            dialog.dismiss()
            assertFalse(dialog.isShowing)
        }
    }
}