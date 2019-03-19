package io.thelimitbreaker.multithemedemo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import io.thelimitbreaker.multithemedemo.utils.ThemeUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navigator: MainActvityNavigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtils.applyTheme(this)
        setContentView(R.layout.activity_main)
        navigator = MainActvityNavigator(this)
        setupNavigationDrawer()
        navigator.toUserFragment()
    }

    private fun setupNavigationDrawer() {

        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }


        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        toggle.syncState()
        drawer_layout.addDrawerListener(toggle)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_users -> {
                drawer_layout.closeDrawer(GravityCompat.START)
                navigator.toUserFragment()
                true
            }
            R.id.action_theme_light->{
                ThemeUtils.setTheme(this,R.style.AppTheme_Base_Light)
                true
            }
            R.id.action_theme_dark->{
                ThemeUtils.setTheme(this,R.style.AppTheme_Base_Dark)
                true
            }
            R.id.action_theme_ocean->{
                ThemeUtils.setTheme(this,R.style.AppTheme_Base_Ocean)
                true
            }
            else -> {
                drawer_layout.closeDrawer(GravityCompat.START)
                true
            }
        }
    }

}
