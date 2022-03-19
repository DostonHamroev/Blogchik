package uz.hamroev.blogchik.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import render.animations.Attention
import render.animations.Render
import uz.hamroev.blogchik.R
import uz.hamroev.blogchik.databinding.ActivityNoInternetBinding
import uz.hamroev.blogchik.utils.NetworkHelper


class NoInternetActivity : AppCompatActivity() {
    lateinit var binding: ActivityNoInternetBinding
    lateinit var networkHelper: NetworkHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoInternetBinding.inflate(layoutInflater)
        setContentView(binding.root)


        networkHelper = NetworkHelper(this)
        checkInternet()


    }


    private fun checkInternet() {
        binding.cardCheckInternet.setOnClickListener {
            if (networkHelper.isNetworkConnected()) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                /*
                * create custom toast
                * animation alert for button */
                val render = Render(this)
                render.setAnimation(Attention.Shake(binding.cardCheckInternet))
                render.start()

                Toast.makeText(
                    this,
                    "${resources.getString(R.string.no_internet)}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}