package com.example.nav_drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle:ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout:DrawerLayout=findViewById(R.id.drawerlayout)
        val navView:NavigationView=findViewById(R.id.nav_view)

        toggle=ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){
                R.id.nav_home -> replaceFragment(HomeFragment(), it.title.toString())
                R.id.nav_message -> replaceFragment(MessageFragment(), it.title.toString())
                R.id.nav_sync -> replaceFragment(SyncFragment(), it.title.toString())
                R.id.nav_trash -> replaceFragment(TrashFragment(), it.title.toString())
                R.id.nav_setting -> replaceFragment(SettingFragment(), it.title.toString())
                R.id.nav_login -> replaceFragment(LoginFragment(), it.title.toString())
                R.id.nav_share -> Toast.makeText(applicationContext,"clicked share",Toast.LENGTH_LONG).show()
                R.id.nav_rate_us -> Toast.makeText(applicationContext,"clicked rate_us",Toast.LENGTH_LONG).show()
            }
            true
        }
    }

    private fun  replaceFragment(fragment: Fragment,title:String){

        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.framelayout,fragment)
        fragmentTransition.commit()
        drawerLayout.closeDrawers()
        setTitle(title)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}