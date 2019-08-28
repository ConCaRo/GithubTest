package trong.test.github.features.nestedrecyclerview

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_gits.*
import trong.test.github.R
import trong.test.github.core.base.BaseFragment
import trong.test.github.core.extension.observeData
import trong.test.github.core.extension.observeFailure
import trong.test.github.core.extension.viewModel
import trong.test.github.core.model.Parent

class NestedRecyclerViewFragment : BaseFragment() {

    lateinit var viewModel: NestedRecyclerViewViewModel

    lateinit var adapter: ParentAdapter

    companion object {
        fun newInstance() = NestedRecyclerViewFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_nested_recylerview

    override fun onCreate() {
        viewModel = viewModel(viewModelFactory) {
            observeData(nestedData, ::renderData)
            observeFailure(failure, ::handleFailure)
        }
    }

    override fun iniBinding(view: View) {}

    override fun iniView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        adapter = ParentAdapter(clickListener = {
            Log.d("OLALA", it.toString())
        }, childClick = {
            Log.d("OLALA", it.toString())
        })
        recyclerView.adapter = adapter

        /*recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!viewModel.isLoadi                  val lastVisibleChild = recyclerView.getChildAt(recyclerView?.childCount - 1)
                    val lastVisiblePos = recyclerView.getChildAdapterPosition(lastVisibleChild)
                    val total = adapter.itemCount
                    if (viewModel.canLoadMore && lastVisiblePos >= (total - 1)) {
                        viewModel.loadMore()
                    }
                }
            }
        })*/
    }

    override fun initData() {
        toolbar("Nested recyclerView")
        viewModel.getData()
    }

    private fun renderData(data: List<Parent>?) {
        adapter.updateItems(data)
    }
}