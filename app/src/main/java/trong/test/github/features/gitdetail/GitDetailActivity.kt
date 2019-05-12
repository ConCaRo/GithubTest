package trong.test.github.features.gitdetail

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import trong.test.github.R
import trong.test.github.core.base.BaseActivity

class GitDetailActivity : BaseActivity() {

    companion object {
        const val PARAM_ID = "id"
        fun callingIntent(context: Context, id: String): Intent = Intent(context, GitDetailActivity::class.java).apply {
            putExtra(PARAM_ID, id)
        }
    }

    override fun layoutId(): Int = R.layout.activity_container

    override fun fragment(): Fragment = GitDetailFragment.newInstance()
}