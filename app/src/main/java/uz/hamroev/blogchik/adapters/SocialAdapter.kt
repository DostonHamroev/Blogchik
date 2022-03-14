package uz.hamroev.blogchik.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import uz.hamroev.blogchik.R
import uz.hamroev.blogchik.databinding.ItemSocialBinding
import uz.hamroev.blogchik.models.social.Item

class SocialAdapter(
    var context: Context,
    var list: List<Item>,
    var onSocialClickListener: OnSocialClickListener
) :
    RecyclerView.Adapter<SocialAdapter.VhSocial>() {


    inner class VhSocial(var itemSocialBinding: ItemSocialBinding) :
        RecyclerView.ViewHolder(itemSocialBinding.root) {

        fun onBind(item: Item, position: Int) {
            itemSocialBinding.socialTitle.text = item.title
            Glide.with(context)
                .load(item.photo_url)
                .error(R.mipmap.ic_logo)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(itemSocialBinding.socialImage)

            itemSocialBinding.cardSocial.setOnClickListener {
                onSocialClickListener.onCLick(item, position)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhSocial {
        return VhSocial(
            ItemSocialBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VhSocial, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnSocialClickListener {
        fun onCLick(item: Item, position: Int)
    }


}