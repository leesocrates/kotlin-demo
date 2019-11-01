package cn.example.myapplication.activity

import android.animation.*
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.animation.AnimationUtils
import android.view.animation.BaseInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import cn.example.baselib.activity.BaseActivity
import cn.example.myapplication.R
import kotlinx.android.synthetic.main.activity_animator.*

class AnimatorActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_animator
    }

    override fun initOnCreate() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.view_animation)
        animation.fillAfter = true
        animation.interpolator = MyInterpolator()
        animator_img.startAnimation(animation)

        val drawableAnimation = animator_drawable.background as AnimationDrawable
        drawableAnimation.start()

        val set = AnimatorInflater.loadAnimator(this, R.animator.property_animator)
        set.setTarget(animator_tv)
        set.start()

        val objectAnimator = ObjectAnimator.ofFloat(animator_object, "rotationY", 18.0f,36.0f)
        objectAnimator.duration = 2000
        objectAnimator.addUpdateListener { animation ->
            val value = animation?.animatedValue
            var oldTexSize = animator_object.textSize
            val newTextSize = ++oldTexSize
            animator_object.setTextSize(TypedValue.COMPLEX_UNIT_DIP, value as Float)
        }
        objectAnimator.start()

        val a1 = PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
        val a2 = PropertyValuesHolder.ofFloat("textSize", 18f, 36f)
        ObjectAnimator.ofPropertyValuesHolder(animator_propertyValue, a1, a2).setDuration(2000).start()

        val animator = ValueAnimator.ofFloat(0f, 20f)  //定义动画
        animator.setTarget(animator_valueAnimator)   //设置作用目标

        animator.addUpdateListener { animation ->
            val value = animation?.animatedValue
            Log.i(TAG, "current value is $value")
            animator_valueAnimator.setTextSize(TypedValue.COMPLEX_UNIT_DIP, value as Float)
        }
        animator.setEvaluator(object: TypeEvaluator<Float>{
            override fun evaluate(fraction: Float, startValue: Float?, endValue: Float?): Float {
                Log.i(TAG, "return $fraction $startValue $endValue")
                return fraction*20
            }

        })
        animator.setDuration(5000).start()

        animator_animateMethod.animate().x(100f).y(100f).start()

//        val animSet = AnimatorSet()
//        animSet.duration = 5000
//        animSet.interpolator = LinearInterpolator()
//        animSet.play(objectAnimator).after(animator)
//        animSet.start()
    }
     class MyInterpolator: Interpolator{
        override fun getInterpolation(input: Float): Float {
            return input
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    class MyPropertyInterpolator: BaseInterpolator(){
        override fun getInterpolation(input: Float): Float {
            return input
        }

    }

}
