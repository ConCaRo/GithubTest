package trong.test.github.features.nestedrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import trong.test.github.core.model.Children
import trong.test.github.core.model.Parent
import trong.test.github.databinding.ItemChildBinding

/**
 * Created by Concaro on 05/11/2017.
 */
class ChildAdapter(var items: List<Children> = arrayListOf(), var clickListener: (Children) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent?.context)
        var itemBinding = ItemChildBinding.inflate(v)
        return ItemViewHolder(itemBinding)
    }

    fun updateItems(items: List<Children>?) {
        items?.let {
            this.items = items
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as ItemViewHolder
        val item = items.get(position)
        holder?.itemBinding.rootView.setOnClickListener {
            clickListener.invoke(item)
        }
        holder?.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(val itemBinding: ItemChildBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Children) {
            itemBinding.data = item
        }
    }
}