package uz.hamroev.blogchik.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import uz.hamroev.blogchik.R
import uz.hamroev.blogchik.adapters.NavMenuAdapter
import uz.hamroev.blogchik.databinding.ActivityHomeBinding
import uz.hamroev.blogchik.models.NavMenu

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var list: ArrayList<NavMenu>
    lateinit var navMenuAdapter: NavMenuAdapter


    var isBack = false
    lateinit var handler: Handler
    var doubleToast = "Please click BACK again to exit"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)

        loadNavMenu()

        navMenuAdapter = NavMenuAdapter(this, list, object : NavMenuAdapter.OnNavCLickListener {
            override fun onClick(navMenu: NavMenu, position: Int) {
                when (position) {
                    0 -> {
                        binding.bottomNavigationView.selectedItemId = R.id.blog
                        navController.popBackStack()
                        navController.navigate(R.id.blogsFragment)
                        binding.drawerLayout.closeDrawers()
                    }
                    1 -> {
                        binding.bottomNavigationView.selectedItemId = R.id.pictures
                        navController.popBackStack()
                        navController.navigate(R.id.picturesFragment)
                        binding.drawerLayout.closeDrawers()
                    }
                    2 -> {
                        binding.bottomNavigationView.selectedItemId = R.id.project
                        navController.popBackStack()
                        navController.navigate(R.id.projectsFragment)
                        binding.drawerLayout.closeDrawers()
                    }
                    3 -> {
                        binding.bottomNavigationView.selectedItemId = R.id.social
                        navController.popBackStack()
                        navController.navigate(R.id.socialFragment)
                        binding.drawerLayout.closeDrawers()
                    }
                    4 -> {
                        try {
                            val uri: Uri = Uri.parse("market://details?id=$packageName")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            val uri: Uri =
                                Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                        binding.drawerLayout.closeDrawers()
                    }
                    5 -> {
                        try {
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.setType("text/plain")
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Blogchik ")
                            val shareMessage: String =
                                "https://play.google.com/store/apps/details?id=" + packageName
                            intent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                            startActivity(Intent.createChooser(intent, "share by"))
                        } catch (e: Exception) {
                        }
                        binding.drawerLayout.closeDrawers()
                    }
                    6 -> {
                        finish()
                        binding.drawerLayout.closeDrawers()
                    }
                }
            }
        })
        binding.rvNav.adapter = navMenuAdapter



        binding.cardMenu.setOnClickListener {
            binding.drawerLayout.open()
        }


        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.blog -> {
                    navController.popBackStack()
                    navController.navigate(R.id.blogsFragment)
                    true
                }
                R.id.pictures -> {
                    navController.popBackStack()
                    navController.navigate(R.id.picturesFragment)
                    true
                }
                R.id.project -> {
                    navController.popBackStack()
                    navController.navigate(R.id.projectsFragment)
                    true
                }
                R.id.social -> {
                    navController.popBackStack()
                    navController.navigate(R.id.socialFragment)
                    true
                }
                else -> false
            }

        }
    }

    private fun loadNavMenu() {
        list = ArrayList()
        list.add(NavMenu("Blogs", R.drawable.ic_blog))
        list.add(NavMenu("Pictures", R.drawable.ic_pictures))
        list.add(NavMenu("Projects", R.drawable.ic_project))
        list.add(NavMenu("Socials", R.drawable.ic_socials))
        list.add(NavMenu("Rate us", R.drawable.ic_rate))
        list.add(NavMenu("Share", R.drawable.ic_share))
        list.add(NavMenu("Exit", R.drawable.ic_exit))
    }


    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else
            if (isBack) {
                super.onBackPressed()
                return
            }

        this.isBack = true
        handler = Handler(Looper.getMainLooper())
        Toast.makeText(this, "$doubleToast", Toast.LENGTH_SHORT).show()
        handler.postDelayed({
            isBack = false
        }, 1000)
    }


}