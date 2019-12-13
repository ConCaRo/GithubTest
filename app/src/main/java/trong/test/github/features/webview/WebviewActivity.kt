package trong.test.github.features.webview

import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.webkit.*
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

        WebView.setWebContentsDebuggingEnabled(true)
//        settings.userAgentString = "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19"
//        settings.userAgentString =
//            "Mozilla/5.0 (Linux; Android 9; Android SDK built for x86 Build/PSR1.180720.075; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 Mobile Safari/537.36"
        //  settings.userAgentString = "Mozilla/5.0 (Linux; Android 9; Android SDK built for x86  Build/shopback-inapp-web-androidPSR1.180720.075shopback-inapp-web-android) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/69.0.3497.100 Mobile Safari/537.36"

        settings.domStorageEnabled = true
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.builtInZoomControls = true
        settings.displayZoomControls = false
        settings.allowFileAccessFromFileURLs = true
        settings.allowUniversalAccessFromFileURLs = true
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webView.setWebViewClient(
            SSLTolerentWebViewClient()
        )
        webView.webChromeClient = MyWebChromeClient()
        webView.loadUrl("https://myfave.com/")


        // feature 1
        // change feature 1
    }

    private inner class SSLTolerentWebViewClient : WebViewClient() {

        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
            handler.proceed() // Ignore SSL certificate errors
        }
    }

    private inner class MyWebChromeClient: WebChromeClient() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onPermissionRequest(request: PermissionRequest?) {
            request?.let { it.grant(it.resources) }
        }
    }
}
