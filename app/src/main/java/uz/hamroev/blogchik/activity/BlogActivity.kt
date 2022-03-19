package uz.hamroev.blogchik.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import uz.hamroev.blogchik.cache.Cache
import uz.hamroev.blogchik.databinding.ActivityBlogBinding

class BlogActivity : AppCompatActivity() {
    lateinit var binding: ActivityBlogBinding
    private val TAG = "BlogActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Cache.init(this)
        Log.d(TAG, "onCreate: ${Cache.blogHTML}")
        loadHtml()


    }

    private fun loadHtml() {
        Log.d(TAG, "loadHtml: ${Cache.blogID}")
        binding.webView.loadUrl("https://blogchik.uz/blog/p/${Cache.blogID}/")
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)
        binding.webView.isSoundEffectsEnabled = true
        binding.webView.settings.setGeolocationEnabled(true)
        binding.webView.requestFocus()
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.databaseEnabled = true
        binding.webView.settings.lightTouchEnabled = true
//        binding.webView.loadData("${Cache.blogHTML}","text/html", "UTF-8")
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else
            super.onBackPressed()
    }
}