package trong.test.github.features.fragmentnestedrecyclerview

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_gits.*
import trong.test.github.R
import trong.test.github.core.base.BaseFragment
import trong.test.github.core.extension.observeData
import trong.test.github.core.extension.observeFailure
import trong.test.github.core.extension.viewModel
import trong.test.github.core.model.Git
import trong.test.github.features.gits.GitAdapter

class GitsFragment1 : BaseFragment() {

    lateinit var viewModel: GitsViewModel1

    lateinit var adapter: GitAdapter

    companion object {
        fun newInstance(position: Int) = GitsFragment1().apply {
            arguments = Bundle().apply {
                putInt("position", position)
            }
        }
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
        tvParent.text = "Hello ${arguments?.getInt("position")}"
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(false)
        adapter = GitAdapter(fragmentPosition = arguments?.getInt("position")?: 0) { navigator.goGitDetail(requireContext(), it.login) }
        recyclerView.adapter = adapter

        if(arguments?.getInt("position") == 10) {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    Log.d("OLALA", "onScrolled child $dx $dy")
                    if (!viewModel.isLoading) {
                        val lastVisibleChild = recyclerView.getChildAt(recyclerView?.childCount - 1)
                        val lastVisiblePos = recyclerView.getChildAdapterPosition(lastVisibleChild)
                        val total = adapter.itemCount
                        if (viewModel.canLoadMore && lastVisiblePos >= (total - 1)) {
                            viewModel.loadMore()
                        }
                        if(!recyclerView.canScrollVertically(-1) && total > 0) {
                            (recyclerView as Recyclerview1).parent_requestDisallowInterceptTouchEvent = false
                        }
                    }
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("OLALA","onViewCreated ${arguments?.getInt("position")}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("OLALA","onDestroyView ${arguments?.getInt("position")}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("OLALA","onDestroy ${arguments?.getInt("position")}")
    }

    override fun initData() {
        //hideToolbar()
        viewModel.getGits(arguments?.getInt("position") ?: 1)
    }

    private fun renderData(gits: List<Git>?) {
        adapter.updateItems(gits)
    }

    fun loadMore() {
        // Fix height of recyclerview
        val layoutParams = recyclerView.layoutParams
        layoutParams.height = recyclerView.height
        recyclerView.layoutParams = layoutParams
        recyclerView.parent_requestDisallowInterceptTouchEvent = true
        viewModel.loadMore()
        Log.d("OLALA", "load more")
    }

    fun setScrollInside(canScroll: Boolean) {

    }
}