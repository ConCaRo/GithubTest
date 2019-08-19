package trong.test.github.features.webview

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import com.amplitude.api.Amplitude
import kotlinx.android.synthetic.main.activity_webview.*
import trong.test.github.R
import trong.test.github.features.utils.AmplitudeEvent

class WebviewActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AmplitudeEvent.trackEvent(AmplitudeEvent.WEBVIEW)
        Amplitude.getInstance().userId = "Anonymous in Webview"
        setContentView(R.layout.activity_webview)
        setupWebView()
    }

    fun setupWebView() {
        val settings = webView.settings
        settings.userAgentString =
            "Mozilla/5.0 (Linux; Android 9; Android SDK built for x86 Build/PSR1.180720.075; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 Mobile Safari/537.36"
        //  settings.userAgentString = "Mozilla/5.0 (Linux; Android 9; Android SDK built for x86  Build/shopback-inapp-web-androidPSR1.180720.075shopback-inapp-web-android) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 Mobile Safari/537.36"

        settings.domStorageEnabled = true
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.builtInZoomControls = true
        settings.displayZoomControls = false
        settings.allowFileAccessFromFileURLs = true
        settings.allowUniversalAccessFromFileURLs = true
        webView.loadUrl("https://www.lufthansa.com/sg/en/homepage?camref=1011l3NSR&pubid=1011l25878")
    }

}
