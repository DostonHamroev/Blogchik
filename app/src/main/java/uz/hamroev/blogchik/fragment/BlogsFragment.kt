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
import uz.hamroev.blogchik.databinding.FragmentBlogsBinding
import uz.hamroev.blogchik.models.blogs.Blogs
import uz.hamroev.blogchik.retrofit.Common
import uz.hamroev.blogchik.retrofit.RetrofitService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlogsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlogsFragment : Fragment() {
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

    lateinit var binding: FragmentBlogsBinding
    private val TAG = "BlogsFragment"
    lateinit var retrofitService: RetrofitService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlogsBinding.inflate(layoutInflater, container, false)

        retrofitService = Common.retrofitService
        retrofitService.getBlogs().enqueue(object : Callback<Blogs> {
            override fun onResponse(call: Call<Blogs>, response: Response<Blogs>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse: ${response.body().toString()}")
                }
            }

            override fun onFailure(call: Call<Blogs>, t: Throwable) {

            }
        })

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlogsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlogsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}