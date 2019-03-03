package io.wuzz.kotlinas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_list_detail.*
import org.jetbrains.anko.toast

class ListDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        // 取到传值
        var id =this.intent.getIntExtra("id",0)
        toast("取到的id"+id)

        //加载webview，并且内容在webview里加载
        wv1.loadUrl("http://www.baidu.com")
        wv1.webViewClient = object : WebViewClient() { //不加的话会自动启用内核浏览器
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
    }
}
