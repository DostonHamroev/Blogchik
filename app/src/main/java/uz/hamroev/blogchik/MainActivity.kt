package uz.hamroev.blogchik

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import uz.hamroev.blogchik.activity.HomeActivity
import uz.hamroev.blogchik.databinding.ActivityMainBinding
import uz.hamroev.blogchik.utils.NetworkHelper

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var networkHelper: NetworkHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
        networkHelper = NetworkHelper(this)

        checkNetwork()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 1500)
    }

    private fun checkNetwork() {
        if (networkHelper.isNetworkConnected()){
            Toast.makeText(this, "Yes Internet", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
        }
    }
}