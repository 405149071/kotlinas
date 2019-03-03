package io.wuzz.kotlinas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_listview.*
import model.Video
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class ListviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)

        // 静态数据处理
        //staticData()

        //动态请求数据
        dataFromNet()

    }

    // 静态数据
    fun staticData(){

        var model1 = Video()
        model1.id = 110
        model1.title = "培训视频1"
        model1.price = 22.00
        model1.time = "2019-09-09"
        model1.img = "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=540100846,1478468760&fm=58"

        var model2 = Video()
        model2.id = 220
        model2.title = "培训视频2"
        model2.price = 23.00
        model2.time = "2019-09-09"
        model2.img="https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2115254197,2697414359&fm=58"


        var model3 = Video()
        model3.id = 330
        model3.title = "培训视频3"
        model3.price = 24.00
        model3.time = "2019-09-09"
        model3.img="https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3407842325,808353898&fm=58"

        var list:List<Video> = listOf(model1,model2,model3)

        lv1.adapter = MyAdapt(list,this)

        toast("静态数据加载完成")

        // 唤起详情页
        lv1.setOnItemClickListener { adapterView, view, i, l ->
            startActivity<ListDetailActivity>(Pair("id", list[i].id))
        }
    }

    fun dataFromNet() {
        // 取远端数据 anko
        doAsync {
            var url: String = "http://10.0.3.2:3000/videos"
            var json: String = URL(url).readText()
            Log.i("videos = ", json)

            var videos: List<Video> = Gson().fromJson(json, object : TypeToken<List<Video>>() {}.type)

            uiThread {

                lv1.adapter = MyAdapt(videos, this@ListviewActivity)
                // 唤起详情页
                lv1.setOnItemClickListener { adapterView, view, i, l ->
                    startActivity<ListDetailActivity>(Pair("id", videos[i].id))
                }

                toast("远程数据获取成功")  //anko 提供的
            }

        }
    }
}
