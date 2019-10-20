package cn.example.baselib.activity

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

abstract class BaseActivity: AppCompatActivity(){
    protected val TAG: String= this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initOnCreate()
    }

    abstract fun getLayoutId(): Int
    abstract fun initOnCreate()

    inline fun <reified T : Fragment> openFragment(@IdRes fragmentContainer: Int, bundle: Bundle? = null) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val f: Fragment = T::class.java.newInstance()
        f.arguments = bundle
        fragmentTransaction.add(fragmentContainer, f, T::class.java.canonicalName)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun openFragment(f: Fragment,tag: String?, isAddToBackStack: Boolean?, @IdRes fragmentContainer: Int){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(fragmentContainer, f, tag)
        if(isAddToBackStack == true){
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }
}