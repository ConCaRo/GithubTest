package trong.test.github

import android.app.Activity
import android.app.Application
import com.amplitude.api.Amplitude
import com.amplitude.api.Identify
import com.frogermcs.androiddevmetrics.AndroidDevMetrics
import com.google.gson.JsonObject
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import org.json.JSONArray
import org.json.JSONObject
import trong.test.github.core.di.AppInjector
import javax.inject.Inject

class GitApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        if (BuildConfig.DEBUG) {
            AndroidDevMetrics.initWith(this)
        }
        Amplitude.getInstance().trackSessionEvents(true)
        Amplitude.getInstance().initialize(this, "ee19aa4452f5f3715ae304ced6082362").enableForegroundTracking(this)
        // User
        Amplitude.getInstance().userId = null
        Amplitude.getInstance().regenerateDeviceId()

        val identify = Identify()
        identify.set("gender", "Male")
        identify.set("name", "Trong")
        identify.add("count", 1).add("application_start", 1)
        identify.append("append", "123")
        identify.append("list", JSONArray().put(1).put("jsonad"))
        Amplitude.getInstance().identify(identify)

        val userProperties = JSONObject()
        userProperties.put("dob", "22-09-2019")
        Amplitude.getInstance().setUserProperties(userProperties)

    }
}