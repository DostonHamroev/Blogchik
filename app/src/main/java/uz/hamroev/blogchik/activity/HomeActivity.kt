package uz.hamroev.blogchik.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import uz.hamroev.blogchik.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}