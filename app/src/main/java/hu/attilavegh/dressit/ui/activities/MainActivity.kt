package hu.attilavegh.dressit.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import hu.attilavegh.dressit.R
import hu.attilavegh.dressit.ui.fragments.cloth.DressFragment
import hu.attilavegh.dressit.ui.fragments.cloth.dummy.DummyContent


class MainActivity : AppCompatActivity(), DressFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_clothes,
                R.id.navigation_shape,
                R.id.navigation_profile
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intent)
    }
}
