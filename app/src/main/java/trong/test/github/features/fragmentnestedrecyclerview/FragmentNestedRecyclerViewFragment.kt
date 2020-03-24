package trong.test.github.features.fragmentnestedrecyclerview

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.fragment_fragment_nested_recylerview.*
import trong.test.github.R
import trong.test.github.core.base.BaseFragment
import trong.test.github.core.extension.observeData
import trong.test.github.core.extension.observeFailure
import trong.test.github.core.extension.viewModel
import trong.test.github.core.model.Parent

class FragmentNestedRecyclerViewFragment : BaseFragment() {

    lateinit var viewModel: FragmentNestedRecyclerViewViewModel

    lateinit var adapter: FragmentNestedRecyclerviewAdapter

    companion object {
        fun newInstance() = FragmentNestedRecyclerViewFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_fragment_nested_recylerview

    override fun onCreate() {
        viewModel = viewModel(viewModelFactory) {
            observeData(nestedData, ::renderData)
            observeFailure(failure, ::handleFailure)
        }
    }

    override fun iniBinding(view: View) {}

    val listFragments = arrayListOf(
//        GitsFragment1.newInstance(1), GitsFragment1.newInstance(2),
//        GitsFragment2.newInstance(3), GitsFragment1.newInstance(4),
//        GitsFragment1.newInstance(5), GitsFragment1.newInstance(6),
//        GitsFragment1.newInstance(7), GitsFragment1.newInstance(8),
    GitsFragment1.newInstance(9),
        GitsFragment1.newInstance(10))

    override fun iniView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(false)
        adapter = FragmentNestedRecyclerviewAdapter(this, listFragments)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // Log.d("OLALA", "onScrolled Parent $dx $dy")
                val lastVisibleChild = recyclerView.getChildAt(recyclerView?.childCount - 1)
                val lastVisiblePos = recyclerView.getChildAdapterPosition(lastVisibleChild)
                val total = adapter.itemCount

                if (!viewModel?.isLoading?.value!! && lastVisiblePos >= (total - 1) && !recyclerView.canScrollVertically(
                        1)) {
                    //Log.d("OLALA", "onScrolled Parent cannot Scroll down Vertically $dx $dy")
                    if (recyclerView.adapter?.itemCount!! >= 1) {
//                         adapter.processLoading(true)
                        when (listFragments.get(listFragments.size - 1)) {
                            is GitsFragment1 -> (listFragments.get(listFragments.size - 1) as GitsFragment1).loadMore()
//                            is GitsFragment2 -> (listFragments.get(listFragments.size - 1) as GitsFragment2).loadMore()
                        }
                    }
//                    else {
//                        recyclerView.post {
//                            adapter.updateItems(listFragments)
//                        }
//                    }
                }
            }
        })
    }

    override fun initData() {
        toolbar("Fragment Nested recyclerView")
        viewModel.getData()
    }

    private fun renderData(data: List<Parent>?) {

    }
}