package cn.example.myapplication.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.R
import cn.example.myapplication.utils.AudioManager
import cn.example.myapplication.utils.AudioRecordTool
import kotlinx.android.synthetic.main.activity_audio_test.*
import java.io.File

/**
 *  author : Lee
 *  date : 2021/4/12
 *  description :
 */
class AudioTestActivity: BaseActivity() {
    var audioRecordTool: AudioRecordTool? = null
    var audioManager: AudioManager? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_audio_test
    }

    override fun initOnCreate() {
        audioRecordTool = AudioRecordTool()
        audioManager = AudioManager(applicationContext)
        audioRecordTool?.init(applicationContext.getExternalFilesDir(null).absolutePath+ File.separator+"recrod.pcm",
                applicationContext.getExternalFilesDir(null).absolutePath+ File.separator+"recrod1.wav")
        startRecord.setOnClickListener {
            audioRecordTool?.start()
            object :CountDownTimer(10*1000, 1000){
                override fun onFinish() {
                    Log.e("AudioTest", "record finish ${System.currentTimeMillis()}")
                    audioRecordTool?.stop()
                    Log.e("AudioTest", "record change to wav finish ${System.currentTimeMillis()}")
                    Toast.makeText(applicationContext, "录音结束", Toast.LENGTH_LONG).show()
                }

                override fun onTick(millisUntilFinished: Long) {
                    Log.e("AudioTest", "millisUntilFinished $millisUntilFinished")
                }

            }.start()
        }

        stopRecord.setOnClickListener {
            audioRecordTool?.stop()
        }

        startPlayRecord.setOnClickListener {
            audioManager?.startPlayWithUrl(applicationContext.getExternalFilesDir(null).absolutePath+ File.separator+"recrod1.wav",
                    onPreparedListener = {
                        Toast.makeText(applicationContext, "录音开始播放", Toast.LENGTH_LONG).show()
                        object :CountDownTimer(10*1000, 1000){
                            override fun onFinish() {
                                audioManager?.stopPlay()
                                Toast.makeText(applicationContext, "录音结束", Toast.LENGTH_LONG).show()
                            }

                            override fun onTick(millisUntilFinished: Long) {
                                Log.e("AudioTest", "paly millisUntilFinished $millisUntilFinished")
                            }

                        }.start()
                    }, completeFinish = {
                Toast.makeText(applicationContext, "录音结束播放", Toast.LENGTH_LONG).show()
            })
        }

        stopPlayRecord.setOnClickListener {
            audioManager?.stopPlay()
        }
    }
}