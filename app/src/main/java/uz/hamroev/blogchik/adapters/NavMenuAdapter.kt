package uz.hamroev.blogchik.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.blogchik.databinding.ItemNavMenuBinding
import uz.hamroev.blogchik.models.NavMenu

class NavMenuAdapter(
    var context: Context,
    var list: ArrayList<NavMenu>,
    var onNavCLickListener: OnNavCLickListener
) :
    RecyclerView.Adapter<NavMenuAdapter.VhNavMenu>() {


    inner class VhNavMenu(var itemNavMenuBinding: ItemNavMenuBinding) :
        RecyclerView.ViewHolder(itemNavMenuBinding.root) {

        fun onBind(navMenu: NavMenu, position: Int) {
            try {
                itemNavMenuBinding.navImage.setImageResource(navMenu.navImage)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            itemNavMenuBinding.navName.text = navMenu.navName

            itemNavMenuBinding.cardMain.setOnClickListener {
                onNavCLickListener.onClick(navMenu, position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhNavMenu {
        return VhNavMenu(
            ItemNavMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VhNavMenu, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnNavCLickListener {
        fun onClick(navMenu: NavMenu, position: Int)
    }

}