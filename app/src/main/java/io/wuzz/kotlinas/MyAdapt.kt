package io.wuzz.kotlinas

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_video.view.*
import model.Video
import org.jetbrains.anko.doAsync
import java.net.URL

/**
 *
 */

class MyAdapt(var list:List<Video> ,var context:Context) : BaseAdapter() {

    // 每一行的视图项绑定
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var v:View = LayoutInflater.from(context).inflate(R.layout.item_video,null)
        var model:Video = list[p0]
        v.item_title.text = model.title
        v.item_price.text = model.price.toString()
        v.item_time.text = model.time
    // 获取图片
        if(model.img!=""){
            Log.i("tupian","开始取数据")
            doAsync {
                Log.i("tupian","img="+model.img)
                val data = URL(model.img).readBytes()
                if(data!=null){
                    val bitmap = BitmapFactory.decodeByteArray(data,0,data.size ) //bitmap
                    v.item_img.setImageBitmap(bitmap)
                }
            }
        }
        return  v
    }

    // 返回某一项
    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return list[p0].id.toLong()
    }

    override fun getCount(): Int {
       return list.count()
    }


}
