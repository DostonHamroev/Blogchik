package uz.hamroev.blogchik.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.hamroev.blogchik.adapters.SocialAdapter
import uz.hamroev.blogchik.databinding.FragmentSocialBinding
import uz.hamroev.blogchik.models.social.Item
import uz.hamroev.blogchik.models.social.Social
import uz.hamroev.blogchik.retrofit.Common
import uz.hamroev.blogchik.retrofit.RetrofitService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SocialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SocialFragment : Fragment() {
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

    lateinit var binding: FragmentSocialBinding
    lateinit var retrofitService: RetrofitService
    lateinit var list: List<Item>
    lateinit var socialAdapter: SocialAdapter

    private  val TAG = "SocialFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSocialBinding.inflate(layoutInflater, container, false)

        retrofitService = Common.retrofitService
        retrofitService.getSocials().enqueue(object : Callback<Social>{
            override fun onResponse(call: Call<Social>, response: Response<Social>) {
                if (response.isSuccessful){
                    list = response.body()!!?.items
                    socialAdapter = SocialAdapter(binding.root.context, list, object : SocialAdapter.OnSocialClickListener{
                        override fun onCLick(item: Item, position: Int) {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
                        }

                    })
                    binding.rvSocial.adapter = socialAdapter
                    Log.d(TAG, "onResponse: ${response.body().toString()}")
                    Log.d(TAG, "onResponse: ${response.body()?.items}")
                }
            }

            override fun onFailure(call: Call<Social>, t: Throwable) {
                
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
         * @return A new instance of fragment SocialFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SocialFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}