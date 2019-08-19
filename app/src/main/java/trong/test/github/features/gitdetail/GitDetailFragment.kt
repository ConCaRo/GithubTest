package trong.test.github.features.gitdetail

import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.fragment_git_detail.*
import org.json.JSONObject
import trong.test.github.core.base.BaseActivity
import trong.test.github.core.base.BaseFragment
import trong.test.github.core.extension.observeData
import trong.test.github.core.extension.observeFailure
import trong.test.github.core.extension.viewModel
import trong.test.github.core.model.Git
import trong.test.github.features.webview.WebviewActivity


class GitDetailFragment : BaseFragment() {

    lateinit var viewModel: GitDetailViewModel
    lateinit var binding: trong.test.github.databinding.FragmentGitDetailBinding

    companion object {
        fun newInstance(): Fragment = GitDetailFragment()
    }

    override fun layoutId(): Int = trong.test.github.R.layout.fragment_git_detail

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
        tvLinkBlog.setOnClickListener {
            /*requireActivity().startActivity(Intent(
                Intent.ACTION_VIEW, Uri.parse(tvLinkBlog.text.toString()!!)))*/
            requireActivity().startActivity(Intent(activity, WebviewActivity::class.java))
        }
    }

    fun renderData(git: Git?) {
        val customEvent = JSONObject()
        customEvent.put("id", git?.id)
        customEvent.put("name", git?.name)
        (activity as BaseActivity)?.trackEvent(jsonObject = customEvent)
        toolbar(git?.name, true)
        binding.data = git
    }

}