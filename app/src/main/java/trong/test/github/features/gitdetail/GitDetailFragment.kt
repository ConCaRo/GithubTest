package trong.test.github.features.gitdetail

import android.support.v4.app.Fragment
import android.view.View
import trong.test.github.R
import trong.test.github.core.base.BaseFragment
import trong.test.github.core.extension.observeData
import trong.test.github.core.extension.observeFailure
import trong.test.github.core.extension.viewModel
import trong.test.github.core.model.Git

class GitDetailFragment : BaseFragment() {

    lateinit var viewModel: GitDetailViewModel
    lateinit var binding: trong.test.github.databinding.FragmentGitDetailBinding

    companion object {
        fun newInstance(): Fragment = GitDetailFragment();
    }

    override fun layoutId(): Int = R.layout.fragment_git_detail

    override fun onCreate() {
        this.viewModel = viewModel(viewModelFactory) {
            observeData(gitDetail, ::renderData)
            observeFailure(failure, ::handleFailure)
        }
    }

    override fun iniBinding(view: View) {
        binding = trong.test.github.databinding.FragmentGitDetailBinding.bind(view!!)
    }

    override fun iniView() {
    }

    override fun initData() {
        viewModel.getGitDetail(activity!!.intent.getStringExtra(GitDetailActivity.PARAM_ID))
    }

    fun renderData(git: Git?) {
        toolbar(git?.name, true)
        binding.data = git
    }

}