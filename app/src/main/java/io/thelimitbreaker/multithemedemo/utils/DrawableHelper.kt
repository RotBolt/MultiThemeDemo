package io.thelimitbreaker.multithemedemo.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import io.thelimitbreaker.multithemedemo.R
import kotlin.random.Random

object DrawableHelper {

    fun getRandCircleBG(context: Context): Drawable {
        val circleBG = ContextCompat.getDrawable(context, R.drawable.circle_bg)
        val mutantBG = circleBG?.mutate()
        DrawableCompat.setTint(mutantBG!!, getRandColor())
        mutantBG.invalidateSelf()
        return mutantBG
    }

    private fun getRandColor(): Int {
        val colors = arrayOf(Color.BLUE, Color.CYAN, Color.GREEN, Color.RED)
        return colors[Random.nextInt(4)]
    }
}