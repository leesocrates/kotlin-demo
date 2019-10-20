package cn.example.myapplication.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

class CircleImageView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)
    }

    private fun circleBitmapByShader(bitmap: Bitmap, edgeWidth: Int, radius: Int): Bitmap {

        var btWidth = bitmap.getWidth()
        var btHeight = bitmap.getHeight()
        // 水平方向开始裁剪的位置
        var btWidthCutSite = 0f
        // 竖直方向开始裁剪的位置
        var btHeightCutSite = 0f
        // 裁剪成正方形图片的边长，未拉伸缩放
        var squareWidth = 0f
        if (btWidth > btHeight) { // 如果矩形宽度大于高度
            btWidthCutSite = (btWidth - btHeight) / 2f
            squareWidth = btHeight.toFloat()
        } else { // 如果矩形宽度不大于高度
            btHeightCutSite = (btHeight - btWidth) / 2f;
            squareWidth = btWidth.toFloat()
        }

        // 设置拉伸缩放比
        var scale = edgeWidth * 1.0f / squareWidth;
        var matrix = Matrix()
        matrix.setScale(scale, scale)

        // 将矩形图片裁剪成正方形并拉伸缩放到控件大小
        var squareBt = Bitmap.createBitmap(bitmap, btWidthCutSite.toInt(), btHeightCutSite.toInt(), squareWidth.toInt(), squareWidth.toInt(), matrix, true)

        // 初始化绘制纹理图
        var bitmapShader = BitmapShader(squareBt, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        // 初始化目标bitmap
        var targetBitmap = Bitmap.createBitmap(edgeWidth, edgeWidth, Bitmap.Config.ARGB_8888)

        // 初始化目标画布
        var targetCanvas = Canvas(targetBitmap)

        // 初始化画笔
        var paint = Paint()
        paint.setAntiAlias(true)
        paint.setShader(bitmapShader)

        // 利用画笔绘制圆形图
        targetCanvas.drawRoundRect(RectF(0f, 0f, edgeWidth.toFloat(), edgeWidth.toFloat()), radius.toFloat(), radius.toFloat(), paint)

        return targetBitmap
    }


}