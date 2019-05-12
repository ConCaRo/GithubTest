package trong.test.github.features.gits

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_gits.*
import trong.test.github.R
import trong.test.github.core.base.BaseFragment
import trong.test.github.core.extension.observeData
import trong.test.github.core.extension.observeFailure
import trong.test.github.core.extension.viewModel
import trong.test.github.core.model.Git

class GitsFragment : BaseFragment() {

    lateinit var viewModel: GitsViewModel

    lateinit var adapter: GitAdapter

    companion object {
        fun newInstance() = GitsFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_gits

    override fun onCreate() {
        viewModel = viewModel(viewModelFactory) {
            observeData(gits, ::renderData)
            observeFailure(failure, ::handleFailure)
        }
    }

    override fun iniBinding(view: View) {}

    override fun iniView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        adapter = GitAdapter { navigator.goGitDetail(requireContext(), it.login) }
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!viewModel.isLoading) {
                    val lastVisibleChild = recyclerView.getChildAt(recyclerView?.childCount - 1)
                    val lastVisiblePos = recyclerView.getChildAdapterPosition(lastVisibleChild)
                    val total = adapter.itemCount
                    if (viewModel.canLoadMore && lastVisiblePos >= (total - 1)) {
                        viewModel.loadMore()
                    }
                }
            }
        })
    }

    override fun initData() {
        viewModel.getGits()
    }

    private fun renderData(gits: List<Git>?) {
        adapter.updateItems(gits)
    }
}