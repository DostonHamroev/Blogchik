package uz.hamroev.blogchik.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.hamroev.blogchik.R
import uz.hamroev.blogchik.cache.Cache
import uz.hamroev.blogchik.databinding.ActivityZoomBinding
import uz.hamroev.blogchik.download.ApiClient

class ZoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityZoomBinding
    private val TAG = "ZoomActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityZoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Cache.init(this)
        loadImage()

    }


    private fun loadImage() {
        binding.imageTitle.text = Cache.imageTitle
        binding.imageDate.text = Cache.imageDate

        Glide.with(this)
            .load(Cache.zoomUrl)
            .error(R.mipmap.ic_logo)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.photoView)

        //Picasso.get().load(Cache.zoomUrl).error(R.mipmap.ic_logo).into(binding.photoView)

    }
}