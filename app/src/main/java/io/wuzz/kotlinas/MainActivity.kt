package io.wuzz.kotlinas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import model.User
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //懒加载
        val user1  by lazy {
            User()
        }

        btn1.setOnClickListener {
            btn1.text="hihihi"
            tv1.text="lalall"


            Toast.makeText(this,"hahalololo", Toast.LENGTH_SHORT).show()
        }

        // 将btn1的事件又覆盖了
//        btn1.onClick {  //anko 语法  ,说版本的事不好用了
//            toast("hahah")
//        }

//        //对象
        var user2 = User();

        btn2.setOnClickListener {
            // 取远端数据 anko
            doAsync {
                var url:String = "http://10.0.3.2:3000/users"
                var json:String = URL(url).readText()
                Log.i("logi",json)

                uiThread {
                    toast("远程数据获取成功")  //anko 提供的
                }
            }

        }
        // listview
        btn3.setOnClickListener {
            startActivity<ListviewActivity>()
        }

    }
}
