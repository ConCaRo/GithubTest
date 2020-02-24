package trong.test.github.features.fragmentnestedrecyclerview

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import trong.test.github.databinding.ItemLoadingPaginatedBinding
import trong.test.github.databinding.LayoutFragmentContainerBinding

/**
 * Created by Concaro on 05/11/2017.
 */
class FragmentNestedRecyclerviewAdapter(
    val fragment: Fragment,
    var listFragments: List<Fragment>,
    var loading: Boolean = false
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (loading && position == itemCount - 1) {
            VIEW_TYPE_LOADING
        } else {
            position
        }
    }

    fun processLoading(loading: Boolean) {
        val previousLoading = this.loading
        this.loading = loading
        if (this.loading != previousLoading) {
            if (previousLoading) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        }
    }

    fun updateItems(items: List<Fragment>?) {
        loading = false
        val position = itemCount
        items?.let {
            this.listFragments = items
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return when (viewType) {
            VIEW_TYPE_LOADING -> {
                val binding = ItemLoadingPaginatedBinding.inflate(layoutInflater, parent, false)
                ItemLoadingViewHolder(binding)
            }
            else -> {
                val binding = LayoutFragmentContainerBinding.inflate(layoutInflater, parent, false)
                ItemViewHolder(fragment, binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("Olala", "onBindViewHolder fragment $position")
        when (holder) {
            is ItemViewHolder -> holder?.bind(listFragments[position], position + 100)
            is ItemLoadingViewHolder -> holder?.bind()
        }
    }

    override fun getItemCount(): Int {
        return listFragments.size + if (loading) 1 else 0
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        Log.d("Olala", "onViewAttachedToWindow fragment ${holder.layoutPosition}")
        if(holder is ItemViewHolder) {
            val fragmentManager = fragment?.childFragmentManager
            fragmentManager?.beginTransaction()
                ?.replace(
                    holder.itemBinding.fragmentContainer.id,
                    listFragments[holder.layoutPosition]
                )
                ?.commit()
        }

    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        // Log.d("Olala", "onViewDetachedFromWindow fragment ${holder.layoutPosition}")
    }


    class ItemViewHolder(val fragment: Fragment, val itemBinding: LayoutFragmentContainerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Fragment, id: Int) {
            itemBinding.fragmentContainer.id = id
        }
    }

    class ItemLoadingViewHolder(itemBinding: ItemLoadingPaginatedBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind() {
        }
    }

    companion object {
        private const val VIEW_TYPE_LOADING = Int.MAX_VALUE - 1
    }
}