package io.thelimitbreaker.multithemedemo.utils

import android.app.Activity
import android.content.Intent
import io.thelimitbreaker.multithemedemo.R

class ThemeUtils {
    companion object ThemeUtils {
        private var sTheme = R.style.AppTheme_Base_Light
        fun setTheme(activity: Activity, themeResId: Int) {
            sTheme = themeResId
            activity.startActivity(Intent(activity, activity.javaClass))
            activity.finish()
        }

        fun applyTheme(activity: Activity){
            activity.setTheme(sTheme)
        }
    }
}