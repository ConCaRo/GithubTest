package trong.test.github.features.fragmentnestedrecyclerview

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import trong.test.github.databinding.LayoutFragmentContainerBinding

/**
 * Created by Concaro on 05/11/2017.
 */
class FragmentNestedRecyclerviewAdapter(val fragment: Fragment, val listFragments: List<Fragment>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent?.context)
        var itemBinding = LayoutFragmentContainerBinding.inflate(v, parent, false)
        return ItemViewHolder(fragment, itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("Olala", "onBindViewHolder fragment $position")
        val holder = holder as ItemViewHolder
        val item = listFragments.get(position)
        holder?.bind(item, position + 100)
    }

    override fun getItemCount(): Int {
        return listFragments.size
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        val holder = holder as ItemViewHolder
        Log.d("Olala", "onViewDetachedFromWindow fragment ${holder.layoutPosition}")

    }

    class ItemViewHolder(val fragment: Fragment, val itemBinding: LayoutFragmentContainerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Fragment, id: Int) {
            val fragmentManager = fragment?.childFragmentManager
            itemBinding.fragmentContainer.id = id
            fragmentManager?.beginTransaction()?.replace(itemBinding.fragmentContainer.id, item)
                ?.commit()
        }
    }
}