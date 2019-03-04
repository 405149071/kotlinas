package io.wuzz.kotlinas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_webview.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class WebviewActivity : AppCompatActivity() {

    //懒加载
    private val mWebView : WebView by lazy{
        wv1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        // 默认加载百度
        wv1.loadUrl("http://www.baidu.com");
        wv1.webViewClient = object : WebViewClient() { //不加的话会自动启用内核浏览器
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        btn.setOnClickListener {

            var url:String = url.text.toString()
            wv1.loadUrl(url)
            wv1.webViewClient = object : WebViewClient() { //不加的话会自动启用内核浏览器
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }
            }
        }

    }
}
