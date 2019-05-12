package trong.test.github.core.extension

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import trong.test.github.core.exception.Failure


fun <L : LiveData<T>, T : Any> LifecycleOwner.observeData(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body) )
}

fun <L : LiveData<Failure>> LifecycleOwner.observeFailure(liveData: L, body: (Failure?) -> Unit) {
    liveData.observe(this, Observer(body))
}