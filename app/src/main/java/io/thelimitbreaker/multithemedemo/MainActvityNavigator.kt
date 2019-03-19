package io.thelimitbreaker.multithemedemo

import androidx.fragment.app.Fragment
import io.thelimitbreaker.multithemedemo.users.ui.fragment.TAG_USER_FRAGMENT
import io.thelimitbreaker.multithemedemo.users.ui.fragment.UsersFragment

class MainActvityNavigator(private val activity: MainActivity) {

    fun toUserFragment() {
        addToBackStack(TAG_USER_FRAGMENT,R.id.frag_container){
            UsersFragment.newInstance()
        }
    }

    private fun addToBackStack(tag: String, layoutId: Int, newInstance: () -> Fragment) {
        val supportFragmentManager = activity.supportFragmentManager
        val frag = supportFragmentManager.findFragmentByTag(tag) ?: newInstance()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right, R.anim.exit_to_left,
                R.anim.enter_from_left, R.anim.exit_to_right
            )
            .replace(layoutId, frag, tag)
            .addToBackStack(tag)
            .commit()
    }
}