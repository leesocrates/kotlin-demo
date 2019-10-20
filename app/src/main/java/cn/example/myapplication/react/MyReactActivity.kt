package cn.example.myapplication.react
//
//import cn.example.baselib.activity.BaseActivity
//import cn.example.myapplication.R
//import android.content.Intent
//import android.os.Build
//import android.widget.LinearLayout
//import com.facebook.react.BuildConfig
//import com.facebook.react.LifecycleState
//import com.facebook.react.ReactInstanceManager
//import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
//import com.facebook.react.shell.MainReactPackage
//import kotlinx.android.synthetic.main.activity_rn.*
//
///**
// * Method 'void androidx.core.net.ConnectivityManagerCompat.<initOnCreate>()' is inaccessible to class 'com.facebook.react.modules.netinfo.NetInfoModule'
// * 这个类暂时还没有调通
// */
//class MyReactActivity : BaseActivity(), DefaultHardwareBackBtnHandler{
//    override fun invokeDefaultOnBackPressed() {
//        super.onBackPressed()
//    }
//
//    override fun getLayoutId(): Int {
//        return R.layout.activity_rn
//    }
//
//    override fun initOnCreate() {
//        val mReactRootView = ReactRootView(this)
//        val mReactInstanceManager = ReactInstanceManager.builder()
//                .setApplication(application)
//                .setBundleAssetName("index.android.bundle")
//                .setJSMainModuleName("index.android")
//                .addPackage(MainReactPackage())
//                .setUseDeveloperSupport(BuildConfig.DEBUG)
//                .setInitialLifecycleState(LifecycleState.RESUMED)
//                .build()
//        mReactRootView.startReactApplication(mReactInstanceManager, "MyReactNativeApp", null)
//        rnContainer.addView(mReactRootView, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))
//    }
//
//}