package trong.test.github.core.base

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_container.*
import kotlinx.android.synthetic.main.toolbar.*
import trong.test.github.R
import trong.test.github.core.di.viewmodel.Injectable
import trong.test.github.core.domain.GitFailure
import trong.test.github.core.exception.Failure
import trong.test.github.features.navigator.Navigator
import trong.test.github.features.utils.AmplitudeEvent
import javax.inject.Inject

/**
 * BaseFragment is class initializing base functions of fragment (using android.support.v4.app.Fragment)
 *
 * @see Fragment
 */
abstract class BaseFragment : Fragment(), Injectable {

    abstract fun layoutId(): Int

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        iniBinding(view)
        iniView()
        initData()
    }

    abstract fun onCreate()

    abstract fun iniBinding(view: View)

    abstract fun iniView()

    abstract fun initData()

    fun toolbar(title: String?, hasBack: Boolean = false) {
        val toolBar = requireActivity()?.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        val supportActionBar = (activity as AppCompatActivity).supportActionBar
        supportActionBar?.title = title
        if (hasBack) {
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                requireActivity().finish(); false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun renderFailure(message: String) {
        val snackBar = Snackbar.make((activity as BaseActivity).fragmentContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction("Done") { }
        snackBar.setActionTextColor(
            ContextCompat.getColor(activity?.applicationContext!!,
                R.color.colorTextPrimary))
        snackBar.show()
    }

    // TODO: Need to improve here, to much error types
    protected fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkError -> renderFailure("NetworkError")
            is Failure.ServerError -> renderFailure("ServerError")
            is GitFailure.GitsListFailure -> renderFailure("GitsListFailure")
            is GitFailure.GitDetailFailure -> renderFailure("GitDetailFailure")
        }
    }
}