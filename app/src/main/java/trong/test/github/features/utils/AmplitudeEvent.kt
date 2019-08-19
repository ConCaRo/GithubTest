package trong.test.github.features.utils

import com.amplitude.api.Amplitude
import org.json.JSONObject

class AmplitudeEvent {
    companion object {
        const val GIT_LIST = "Git List"
        const val GIT_DETAIL = "Git Detail"
        const val WEBVIEW = "Webview"


        fun trackEvent(event: String) {
            trackEvent(event, null)
        }

        fun trackEvent(event: String, jsonObject: JSONObject? = null) {
            if(jsonObject == null) {
                Amplitude.getInstance().logEvent(event)
            } else {
                Amplitude.getInstance().logEvent(event, jsonObject)
            }
        }
    }
}