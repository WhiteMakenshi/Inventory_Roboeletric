package com.makenshi.inventory

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher

fun clickInChild(id: Int): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View?> = withId(id)

        override fun getDescription(): String = "Child in ViewHolder."

        override fun perform(
            uiController: UiController?,
            view: View
        ) {
            (view.findViewById<View>(id)!!).performClick()
        }

    }
}