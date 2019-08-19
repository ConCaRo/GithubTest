package trong.test.github.core.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.toolbar.*
import org.json.JSONObject
import trong.test.github.R
import trong.test.github.core.extension.inTransaction
import trong.test.github.features.gitdetail.GitDetailActivity
import trong.test.github.features.gits.GitsActivity
import trong.test.github.features.utils.AmplitudeEvent
import javax.inject.Inject

/**
 * BaseActivity is class for initializing base functions for Activity
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    abstract fun layoutId(): Int

    abstract fun fragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trackEvent()
        setContentView(layoutId())
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(
                R.id.fragmentContainer, fragment()
            )
        }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    fun trackEvent(event: String? = null, jsonObject: JSONObject? = null) {
        if (event == null) {
            var eventName = this.javaClass.simpleName
            when (this) {
                is GitsActivity -> eventName = AmplitudeEvent.GIT_LIST
                is GitDetailActivity -> eventName = AmplitudeEvent.GIT_DETAIL
            }
            AmplitudeEvent.trackEvent(eventName, jsonObject)
        } else {
            AmplitudeEvent.trackEvent(event, jsonObject)
        }
    }

    fun trackEvent() {
        trackEvent(null)
    }
}