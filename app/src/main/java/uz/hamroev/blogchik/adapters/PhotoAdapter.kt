package uz.hamroev.blogchik.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import uz.hamroev.blogchik.R
import uz.hamroev.blogchik.databinding.ItemPhotoBinding
import uz.hamroev.blogchik.models.photos.Item

class PhotoAdapter(
    var context: Context,
    var list: List<Item>,
    var onPhotoClickListener: OnPhotoClickListener
) :
    RecyclerView.Adapter<PhotoAdapter.VhPhotos>() {


    inner class VhPhotos(var itemPhotoBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(itemPhotoBinding.root) {

        fun onBind(item: Item, position: Int) {

            Glide.with(context)
                .load(item.url)
                .error(R.mipmap.ic_logo)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(itemPhotoBinding.photoUrl)

            itemPhotoBinding.photoTitle.text = item.title
            itemPhotoBinding.cardPhoto.setOnClickListener {
                onPhotoClickListener.OnClickPhoto(item, position)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhPhotos {
        return VhPhotos(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VhPhotos, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnPhotoClickListener {
        fun OnClickPhoto(item: Item, position: Int)
    }
}