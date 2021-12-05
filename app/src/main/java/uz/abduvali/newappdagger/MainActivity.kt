package uz.abduvali.newappdagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import uz.abduvali.newappdagger.databinding.ActivityMainBinding
import uz.abduvali.newappdagger.utils.hide
import uz.abduvali.newappdagger.utils.show

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController =
            (supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment).navController
        binding.apply {
            bottomNavigation.setupWithNavController(navController)
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> {
                        navController.navigate(R.id.homeFragment)
                    }
                    R.id.menu_category -> {
                        navController.navigate(R.id.categoriesFragment)
                    }
                    R.id.menu_bookmark -> {
                        navController.navigate(R.id.bookmarksFragment)
                    }
                    R.id.menu_settings -> {
                        navController.navigate(R.id.settingsFragment)
                    }
                }
                true
            }
        }
    }

    fun showNavigation() {
        binding.bottomNavigation.show()
    }

    fun hideNavigation() {
        binding.bottomNavigation.hide()
    }
}