package trong.test.github.features.nestedrecyclerview

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import trong.test.github.core.model.Children
import trong.test.github.core.model.Parent
import trong.test.github.databinding.ItemParentBinding

/**
 * Created by Concaro on 05/11/2017.
 */
class ParentAdapter(var items: List<Parent> = arrayListOf(), var clickListener: (Parent) -> Unit, var childClick: (Children) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent?.context)
        var itemBinding = ItemParentBinding.inflate(v)
        return ItemViewHolder(itemBinding, childClick)
    }

    fun updateItems(items: List<Parent>?) {
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

    class ItemViewHolder(val itemBinding: ItemParentBinding, private val childClick: (Children) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Parent) {
            itemBinding.data = item

            if(itemBinding.rcParent.adapter == null) {
                val viewPool = RecyclerView.RecycledViewPool()
                val linearLayoutManager = LinearLayoutManager(itemBinding.rcParent.context, LinearLayout.HORIZONTAL, false)
                linearLayoutManager.initialPrefetchItemCount = 4
                itemBinding.rcParent.apply {
                    layoutManager = linearLayoutManager
                    adapter = ChildAdapter(item.data, childClick)
                    setRecycledViewPool(viewPool)
                }
            } else {
                itemBinding.rcParent.adapter?.notifyDataSetChanged()
            }
        }
    }
}