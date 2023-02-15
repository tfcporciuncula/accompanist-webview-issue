package com.fredporciuncula.accompanist.webviewissue

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.fredporciuncula.accompanist.webviewissue.ui.theme.AccompanistWebViewIssueTheme
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AccompanistWebViewIssueTheme {
        val url = "https://planetwild.com/privacy-policy/"
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          // Accompanist webview gets stuck with a redirect when JS is enabled, meaning you can never navigate back
          // out of the webview/app since it keeps reloading the redirect
          WebView(
            state = rememberWebViewState(url),
            onCreated = {
              it.settings.javaScriptEnabled = true
            },
          )

          // Non-accompanist webview behaves fine
//          val context = LocalContext.current
//          val webView = remember { WebView(context) }
//          BackHandler(webView.canGoBack()) { webView.goBack() }
//          AndroidView(
//            factory = {
//              webView.apply {
//                webViewClient = WebViewClient()
//                settings.javaScriptEnabled = true
//                loadUrl(url)
//              }
//            },
//            modifier = Modifier.fillMaxSize(),
//          )
        }
      }
    }
  }
}
