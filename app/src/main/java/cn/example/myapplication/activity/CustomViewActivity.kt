package cn.example.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.R
import cn.example.myapplication.entity.MinuteData
import cn.example.myapplication.view.KLineView
import cn.example.myapplication.view.MinuteView
import kotlinx.android.synthetic.main.activity_view.*

class CustomViewActivity : BaseActivity(){
    override fun getLayoutId(): Int {
        return R.layout.activity_view
    }

    override fun initOnCreate() {
        val minuteData = MinuteData()
        minuteData.min = 10f
        minuteData.max = 10.5F
        minuteData.lastDayPrice = 10.2F
        val data = Array<FloatArray>(10){FloatArray(3)}
        data[0][1] = 10.2F
        data[1][1] = 10.1F
        data[2][1] = 10.15F
        data[3][1] = 10.2F
        data[4][1] = 10.3F
        data[5][1] = 10.35F
        data[6][1] = 10.4F
        data[7][1] = 10.5F
        data[8][1] = 10.4F
        data[9][1] = 10.45F

        minuteData.data = data

        val minuteView = KLineView(this, minuteData)
        minuteViewLayout.addView(minuteView)
    }

}