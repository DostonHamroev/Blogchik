package uz.hamroev.blogchik.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.blogchik.databinding.ItemBlogsBinding
import uz.hamroev.blogchik.models.blogs.Item

class BlogAdapter(
    var context: Context,
    var list: List<Item>,
    var onBlogClickListener: OnBlogClickListener
) :
    RecyclerView.Adapter<BlogAdapter.VhBlogs>() {


    inner class VhBlogs(var itemBlogsBinding: ItemBlogsBinding) :
        RecyclerView.ViewHolder(itemBlogsBinding.root) {

        fun onBind(item: Item, position: Int) {
            itemBlogsBinding.blogTime.text = item.date
            itemBlogsBinding.blogDescription.text = item.description
            itemBlogsBinding.blogTitle.text = item.title

            itemBlogsBinding.cardBlog.setOnClickListener {
                onBlogClickListener.onClick(item, position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhBlogs {
        return VhBlogs(ItemBlogsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VhBlogs, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnBlogClickListener {
        fun onClick(item: Item, position: Int)
    }
}