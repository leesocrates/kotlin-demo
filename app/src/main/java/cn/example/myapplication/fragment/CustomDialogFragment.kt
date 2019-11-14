package cn.example.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import cn.example.myapplication.R

/**
 *  author : Lee
 *  date : 2019/11/13
 *  description :
 */
class CustomDialogFragment: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_picasso, null)
    }

    fun isShowing(): Boolean {
        try {
            val mShownByMe = DialogFragment::class.java.getDeclaredField("mShownByMe")
            mShownByMe.isAccessible = true
            isAdded
            return mShownByMe.getBoolean(this)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
            return true
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            return true
        }

    }
}