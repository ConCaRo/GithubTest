package trong.test.github.core.base

import android.content.Context
import trong.test.github.core.extension.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Injectable for return the status of network state
 */
@Singleton
class NetworkHandler @Inject constructor(val context: Context) {
    val isConnected: Boolean? get() = context.networkInfo?.isConnected

}