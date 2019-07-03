package com.advertise.cuibin.advertisetest

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    //跳过倒计时提示5秒
    private var recLen = 5

    private var handler: Handler = Handler()

    private var runnable: Runnable? = null


    internal var timer = Timer()

    internal var task: TimerTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        init()
        timer.schedule(task, 0 , 1000)
        handler.postDelayed(runnable, 5000)
    }

    private fun init() {

        tv_skip.setOnClickListener{
            if (runnable != null) {
                handler!!.removeCallbacks(runnable)
            }
            jump_activity()
        }

        runnable = object : Runnable {
            override fun run() {
                // TODO Auto-generated method stub
                jump_activity()
            }
        }

        task = object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    tv_skip.setText("跳过$recLen")
                    if (recLen < 1) {
                        timer.cancel()
                        tv_skip.setVisibility(View.GONE)//倒计时到0隐藏字体
                    }
                    recLen--
                }
            }
        }
    }

    private fun jump_activity() {
        val intentMainActivity = Intent(this, ContentActivity::class.java)
        this.startActivity(intentMainActivity)
    }

}
