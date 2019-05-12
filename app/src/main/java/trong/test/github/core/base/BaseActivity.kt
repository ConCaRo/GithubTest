package trong.test.github.core.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.toolbar.*
import trong.test.github.R
import trong.test.github.core.di.viewmodel.Injectable
import trong.test.github.core.extension.inTransaction
import javax.inject.Inject

/**
 * BaseActivity is class for initializing base functions for Activity
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity(), Injectable, HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    abstract fun layoutId(): Int

    abstract fun fragment() : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction { add(
            R.id.fragmentContainer, fragment()) }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

}