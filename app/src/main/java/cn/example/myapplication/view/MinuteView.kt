package cn.example.myapplication.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import cn.example.myapplication.R
import cn.example.myapplication.entity.MinuteData
import com.lee.library.util.dp2Px
import java.lang.Exception


class MinuteView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseView(context, attrs, defStyleAttr) {
    private lateinit var minuteData: MinuteData
    private var textHeight = 84f
    private var path = Path()

    constructor(context: Context, initData: MinuteData) : this(context, null, 0) {
        minuteData = initData
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun onDraw(canvas: Canvas?) {
        Log.i(TAG, "minuteView onDraw start")
        if (bitmap == null || bitmap!!.isRecycled) {
            setupCanvas()
        }

        bitmap?.let {
            Log.i(TAG, "minuteView onDraw draw bitmap $mWidth $mHeight")
            canvas?.drawBitmap(bitmap!!, 0f, 0f, paint)
        }

        super.onDraw(canvas)
    }

    private fun setupCanvas() {
        if (mCanvas == null) {
            mCanvas = Canvas()
        }
        bitmap?.recycle()

        try {
            bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        } catch (e: Exception) {
            bitmap = null
            return
        } catch (e: OutOfMemoryError) {
            bitmap = null
            return
        }

        mCanvas?.setBitmap(bitmap)
        paint()
    }

    private fun paint() {
        paintFrame()
        paintAvgLine()
        paintTime()
        paintMinuteLine()
    }

    private fun paintFrame() {
        paint.color = context.resources.getColor(R.color.color_ccc)
        paint.style = Paint.Style.STROKE
        mCanvas?.drawRect(0f, 0f, mWidth.toFloat(), mHeight - textHeight, paint)
        paint.style = Paint.Style.FILL
    }

    private fun paintAvgLine() {
        var height = when {
            minuteData.lastDayPrice > minuteData.max -> 0f
            minuteData.lastDayPrice < minuteData.min -> mHeight - textHeight
            else -> getLineY(minuteData.max, minuteData.min, minuteData.lastDayPrice)
        }
        paint.color = 0xffff0000.toInt()
        for (i in 0..mWidth step 30) {
            mCanvas?.drawLine(i.toFloat(), height, (i + 20).toFloat(), height, paint)
        }
    }

    private fun paintMinuteLine() {
        path.reset()
        path.moveTo(0f, mHeight - textHeight)
        paint.setShader(null)
        paint.strokeWidth = 3.0f
        paint.style = Paint.Style.STROKE
        var lastX = 0f
        var lastY = getLineY(minuteData.max, minuteData.min, minuteData.data[0][1])
        for ((index, data) in minuteData.data.withIndex()) {

            val y = getLineY(minuteData.max, minuteData.min, data[1])
            val x = (index.toFloat() / 30) * mWidth
            Log.i(TAG, "index $index, size ${minuteData.data.size} data[1] ${data[1]}, $x, $y")
            path.lineTo(x, y)
            mCanvas?.drawLine(lastX, lastY, x, y, paint)
            lastX = x
            lastY = y
        }
        paint.strokeWidth = 1f
        val linearGradient = LinearGradient(0f, mHeight - textHeight, lastX, mHeight - textHeight, 0x2200ff00.toInt(), 0x22ff0000.toInt(), Shader.TileMode.CLAMP)
        paint.shader = linearGradient
        paint.style = Paint.Style.FILL
//        paint.color = 0xff00ff00.toInt()
        path.lineTo(lastX, mHeight - textHeight)
        path.close()
        mCanvas?.drawPath(path, paint)
    }

    private fun getLineY(max: Float, min: Float, value: Float): Float {
        return (max - value) / (max - min) * (mHeight - textHeight)
    }

    private fun paintTime() {
        paint.textSize = dp2Px(18f)
        paint.color = 0xffff0000.toInt()
        paint.isAntiAlias = true
        val xCordinal = intArrayOf(1, (width - 1 - paint.measureText("00:00").toInt()) / 2, width - 1 - paint.measureText("00:00").toInt())

        paintTextCenterOfTheLine(minuteData.startTime, xCordinal[0], mHeight - textHeight / 2)
        paintTextCenterOfTheLine(minuteData.endTime, xCordinal[1], mHeight - textHeight / 2)
    }

    private fun paintTextCenterOfTheLine(text: String, x: Int, y: Float) {
        Log.i(TAG, "fontMetrics is ${paint.fontMetrics.top} ${paint.fontMetrics.bottom} ${paint.fontMetrics.ascent} ${paint.fontMetrics.descent}")
        val fontMetrics = paint.fontMetrics
        val fontHeight = fontMetrics.bottom - fontMetrics.top
        val baseLineY = y + fontHeight / 2 - fontMetrics.bottom
//        val baseLineY = y + getBaseline(paint)
        Log.i(TAG, "basey is ${fontHeight / 2 - fontMetrics.bottom} ${getBaseline(paint)} $textHeight")
        mCanvas?.drawText(text, x.toFloat(), baseLineY, paint)
    }

    /**
     * 计算绘制文字时的基线到中轴线的距离
     *
     * @param p
     * @param centerY
     * @return 基线和centerY的距离
     */
    fun getBaseline(p: Paint): Float {
        val fontMetrics = p.getFontMetrics()
        return (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent
    }


}