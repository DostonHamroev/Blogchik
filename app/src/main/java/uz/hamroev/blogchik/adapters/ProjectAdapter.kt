package uz.hamroev.blogchik.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.blogchik.databinding.ItemProjectBinding
import uz.hamroev.blogchik.models.projects.Item

class ProjectAdapter(
    var context: Context,
    var list: List<Item>,
    var onProjectClickListener: OnProjectClickListener
) :
    RecyclerView.Adapter<ProjectAdapter.VhProject>() {

    inner class VhProject(var itemProjectBinding: ItemProjectBinding) :
        RecyclerView.ViewHolder(itemProjectBinding.root) {


        fun onBing(item: Item, position: Int) {

            itemProjectBinding.projectDescription.text = item.description
            itemProjectBinding.projectTime.text = item.date
            itemProjectBinding.projectTitle.text = item.title

            itemProjectBinding.cardProject.setOnClickListener {
                onProjectClickListener.onClick(item, position)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhProject {
        return VhProject(
            ItemProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VhProject, position: Int) {
        return holder.onBing(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface OnProjectClickListener {

        fun onClick(item: Item, position: Int)
    }
}