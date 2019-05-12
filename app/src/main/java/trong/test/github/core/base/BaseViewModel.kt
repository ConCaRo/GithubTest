package trong.test.github.core.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import trong.test.github.core.exception.Failure

/**
 * BaseViewModel is a base class for initializing base functions for ViewModel
 *
 * @see ViewModel
 */
abstract class BaseViewModel : ViewModel() {

    val failure = MutableLiveData<Failure>()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}