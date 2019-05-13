package trong.test.github.features.navigator

import android.content.Context
import trong.test.github.features.gitdetail.GitDetailActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Responsible for navigate between Activities
 */
@Singleton
class Navigator @Inject constructor() {

    fun goGitDetail(context: Context, id: String) {
        context.startActivity(GitDetailActivity.callingIntent(context, id))
    }
}