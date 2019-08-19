package trong.test.github.features.gits

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import trong.test.github.R
import trong.test.github.core.model.Git
import trong.test.github.databinding.ItemGitBinding

/**
 * Created by Concaro on 05/11/2017.
 */
class GitAdapter(var items: List<Git> = arrayListOf(), var clickListener: (Git) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        /*val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_git, parent, false)
        var itemBinding = ItemGitBinding.bind(v)*/
        val v = LayoutInflater.from(parent?.context)
        var itemBinding = ItemGitBinding.inflate(v)
        return ItemViewHolder(itemBinding)
    }

    fun updateItems(items: List<Git>?) {
        items?.let {
            this.items = items
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as ItemViewHolder
        val item = items.get(position)
        /*holder?.itemBinding.data = item
        itemBinding.rootView.setOnClickListener {
            clickListener.invoke(item)
        }
        holder?.itemBinding?.executePendingBindings()*/
        holder?.itemBinding.rootView.setOnClickListener {
            clickListener.invoke(item)
        }
        holder?.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(val itemBinding: ItemGitBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Git) {
            itemBinding.data = item
            // itemBinding.executePendingBindings()
        }
    }
}