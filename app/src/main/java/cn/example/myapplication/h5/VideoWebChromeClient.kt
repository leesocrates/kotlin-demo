package cn.example.myapplication.h5

import android.media.MediaPlayer
import android.util.Log
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
import com.tencent.smtt.sdk.WebChromeClient

/**
 *  author : Lee
 *  date : 2019/10/23
 *  description :
 */
open class VideoWebChromeClient() : WebChromeClient() {

    private var videoContainer: ViewGroup? = null
    private var isVideoFullscreen: Boolean = false
    private var videoViewCallback: IX5WebChromeClient.CustomViewCallback? = null
    private var videoView: View? = null
    private var toggledFullscreenCallback: ToggledFullscreenCallback? = null

    constructor(videoContainer: ViewGroup) : this() {
        this.videoContainer = videoContainer
    }

    override fun onShowCustomView(view: View?, customViewCallback: IX5WebChromeClient.CustomViewCallback?) {
        Log.i("VideoFullScreenTest", "onShowCustomView：start")
        if (view is FrameLayout) {
            // A video wants to be shown
            this.videoView = view

            // Save video related variables
            this.isVideoFullscreen = true
            this.videoViewCallback = customViewCallback

            // Hide the non-video view, add the video view, and show it
            videoContainer?.addView(view, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

            toggledFullscreenCallback?.toggledFullscreen(true)
        }
    }

    override fun onHideCustomView() {
        Log.i("VideoFullScreenTest", "onHideCustomView：start")
        if (isVideoFullscreen) {
            // Hide the video view, remove it, and show the non-video view
            videoContainer?.removeView(videoView)

            // Call back (only in API level <19, because in API level 19+ with chromium webview it crashes)
            if (videoViewCallback != null && !videoViewCallback!!::class.java.name.contains(".chromium.")) {
                videoViewCallback?.onCustomViewHidden()
            }

            // Reset video related variables
            isVideoFullscreen = false
            videoView = null
            videoViewCallback = null

            toggledFullscreenCallback?.toggledFullscreen(false)
        }
    }

    fun setOnToggledFullscreen(callback: ToggledFullscreenCallback) {
        this.toggledFullscreenCallback = callback
    }

    interface ToggledFullscreenCallback {
        fun toggledFullscreen(fullscreen: Boolean)
    }
}