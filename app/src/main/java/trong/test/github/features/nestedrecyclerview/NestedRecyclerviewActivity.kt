package trong.test.github.features.nestedrecyclerview

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import trong.test.github.R
import trong.test.github.core.base.BaseActivity

class NestedRecyclerviewActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context): Intent = Intent(context, NestedRecyclerviewActivity::class.java)
    }

    override fun layoutId(): Int = R.layout.activity_container

    override fun fragment(): Fragment = NestedRecyclerViewFragment.newInstance()
}