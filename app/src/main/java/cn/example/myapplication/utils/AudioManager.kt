package cn.example.myapplication.utils

import android.content.Context
import android.media.MediaPlayer
import android.text.TextUtils


class AudioManager(var context: Context) {
    private var mMediaPlayer: MediaPlayer? = null

    fun startPlay(fileName: String, onPreparedListener: (() -> Unit)?, completeFinish: (() -> Unit)?) {
        startPlay(fileName, null, onPreparedListener, completeFinish)
    }

    fun startPlayWithUrl(url: String?, onPreparedListener: (() -> Unit)?, completeFinish: (() -> Unit)?) {
        startPlay(null, url, onPreparedListener, completeFinish)
    }
    private fun startPlay(fileName: String?, url: String?, onPreparedListener: (() -> Unit)?, completeFinish: (() -> Unit)?){
        if(TextUtils.isEmpty(url) && TextUtils.isEmpty(fileName)){
            return
        }
        destroyPlay()
        try {
            if (mMediaPlayer != null) {
                mMediaPlayer?.start()
            } else {
                mMediaPlayer = MediaPlayer().apply {
                    fileName?.let {
                        val assetFileDescriptor = context.assets.openFd(fileName)
                        reset()
                        setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.length)
                    }
                    url?.let {
                        reset()
                        setDataSource(url)
                    }
                    prepareAsync()
                    setOnPreparedListener {
                        //装载完毕，开始播放
                        start()
                        onPreparedListener?.invoke()
                    }
                    setOnCompletionListener {
                        //播放完成
                        completeFinish?.invoke()
                    }
                }
            }
        } catch (e: Exception) {
        }
    }

    fun replay(isFromStart: Boolean = false) {
        try {
            mMediaPlayer?.apply {
                if (isFromStart && isPlaying) {
                    seekTo(0)
                    start()
                }
                if (!isPlaying) {
                    start()
                }
            }
        } catch (exception: java.lang.Exception) {
        }
    }

    fun stopPlay() {
        try {
            mMediaPlayer?.apply {
                if (isPlaying) {
                    pause()
                }
            }
        } catch (e: Exception) {
        }
    }

    /**
     * 销毁播放
     */
    fun destroyPlay() {
        try {
            mMediaPlayer?.apply {
                stop()
                release()
            }
        } catch (e: java.lang.Exception) {
            mMediaPlayer = null
        } finally {
            mMediaPlayer = null
        }
    }
}