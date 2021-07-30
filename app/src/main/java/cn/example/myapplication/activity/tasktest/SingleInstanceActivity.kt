package cn.example.myapplication.activity.tasktest

import android.content.Intent
import android.util.Log
import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.R
import kotlinx.android.synthetic.main.activity_task_one.*

/**
 *  author : Lee
 *  date : 2020/11/28
 *  description :
 */
class SingleInstanceActivity: BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_task_one
    }

    override fun initOnCreate() {
        taskTv.text = "task singleInstance"
        taskTv.setOnClickListener {
            startActivityForResult(Intent(this, TaskOneActivity::class.java), 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e(TAG, "task singleInstance onActivityResult invoke")
    }

}