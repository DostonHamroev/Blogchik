package uz.hamroev.blogchik.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.hamroev.blogchik.adapters.ProjectAdapter
import uz.hamroev.blogchik.databinding.FragmentProjectsBinding
import uz.hamroev.blogchik.models.projects.Item
import uz.hamroev.blogchik.models.projects.Projects
import uz.hamroev.blogchik.retrofit.Common
import uz.hamroev.blogchik.retrofit.RetrofitService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProjectsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProjectsFragment : Fragment() {
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


    lateinit var binding: FragmentProjectsBinding
    private val TAG = "ProjectsFragment"
    lateinit var retrofitService: RetrofitService

    lateinit var list: List<Item>
    lateinit var projectAdapter: ProjectAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProjectsBinding.inflate(layoutInflater, container, false)

        retrofitService = Common.retrofitService
        retrofitService.getProjects().enqueue(object : Callback<Projects> {
            override fun onResponse(call: Call<Projects>, response: Response<Projects>) {
                if (response.isSuccessful) {
                    if (response.body()!!.ok) {
                        binding.lottiEmpty.visibility = View.GONE
                        binding.rvProject.visibility = View.VISIBLE
                        list = response.body()!!.items
                        loadProjects(list)
                    } else {
                        binding.lottiEmpty.visibility = View.VISIBLE
                        binding.rvProject.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<Projects>, t: Throwable) {

            }

        })

        return binding.root
    }

    private fun loadProjects(list: List<Item>) {
        projectAdapter = ProjectAdapter(
            binding.root.context,
            list,
            object : ProjectAdapter.OnProjectClickListener {
                override fun onClick(item: Item, position: Int) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
                }
            })
        binding.rvProject.adapter = projectAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProjectsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProjectsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}