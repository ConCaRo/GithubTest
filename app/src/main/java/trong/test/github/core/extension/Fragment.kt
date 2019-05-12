package trong.test.github.core.extension

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider.Factory
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

inline fun <reified VM : ViewModel> Fragment.viewModel(factory: Factory, body: VM.() -> Unit): VM {
    val vm = ViewModelProviders.of(this, factory)[VM::class.java]
    vm.body()
    return vm
}