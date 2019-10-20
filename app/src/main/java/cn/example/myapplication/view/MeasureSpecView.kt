package cn.example.myapplication.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class MeasureSpecView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {
    private val text = "hello"
    private val paint = Paint()

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var widthModeStr: String? = null
        var heightModeStr: String? = null
        var resultWidth : Int = widthSize
        var resultHeight: Int = heightSize
        when(widthMode){
            MeasureSpec.EXACTLY ->{
                widthModeStr = "EXACTLY"
            }
            MeasureSpec.AT_MOST ->{
                widthModeStr = "AT_MOST"
            }
            MeasureSpec.UNSPECIFIED ->{
                widthModeStr = "UNSPECIFIED"
                resultWidth = widthSize
            }
        }
        when(heightMode){
            MeasureSpec.EXACTLY ->{
                heightModeStr = "EXACTLY"
            }
            MeasureSpec.AT_MOST ->{
                heightModeStr = "AT_MOST"
            }
            MeasureSpec.UNSPECIFIED ->{
                heightModeStr = "UNSPECIFIED"
                resultHeight = heightSize
            }
        }
        setMeasuredDimension(resultWidth, resultHeight)
        Log.i("MeasureSpecView", "$widthModeStr $widthSize $heightModeStr $heightSize")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.textSize = 48f
        canvas?.drawText(text, 100f, 100f, paint)
    }
}
