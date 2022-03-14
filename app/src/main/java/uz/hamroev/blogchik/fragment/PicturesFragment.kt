package uz.hamroev.blogchik.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.hamroev.blogchik.adapters.PhotoAdapter
import uz.hamroev.blogchik.databinding.FragmentPicturesBinding
import uz.hamroev.blogchik.models.photos.Item
import uz.hamroev.blogchik.models.photos.Photo
import uz.hamroev.blogchik.retrofit.Common
import uz.hamroev.blogchik.retrofit.RetrofitService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PicturesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PicturesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentPicturesBinding
    private val TAG = "PicturesFragment"
    lateinit var retrofitService: RetrofitService

    lateinit var list: List<Item>
    lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPicturesBinding.inflate(layoutInflater, container, false)

        retrofitService = Common.retrofitService
        retrofitService.getPhotos().enqueue(object : Callback<Photo> {
            override fun onResponse(call: Call<Photo>, response: Response<Photo>) {
                if (response.isSuccessful) {
                    if (response.body()?.ok == true) {
                        binding.lottiEmpty.visibility = View.GONE
                        binding.rvPictures.visibility = View.VISIBLE
                        list = response.body()!!?.items
                        loadData(list)

                    } else {
                        binding.rvPictures.visibility = View.GONE
                        binding.lottiEmpty.visibility = View.VISIBLE

                    }
                    Log.d(TAG, "onResponse: ${response.body().toString()}")
                }
            }

            override fun onFailure(call: Call<Photo>, t: Throwable) {

            }
        })


        return binding.root
    }

    private fun loadData(list: List<Item>) {
        photoAdapter = PhotoAdapter(binding.root.context, list, object : PhotoAdapter.OnPhotoClickListener{
            override fun OnClickPhoto(item: Item, position: Int) {

            }

        })
        binding.rvPictures.adapter = photoAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PicturesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PicturesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}