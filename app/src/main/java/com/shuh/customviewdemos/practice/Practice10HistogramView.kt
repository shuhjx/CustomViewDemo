package com.shuh.customviewdemos.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.View

/**
 * Created by pc-135 on 2017/7/24.
 */
class Practice10HistogramView : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var width = 550f
    private var height = 350f
    private val margin = 50f
    private val datas = linkedMapOf("Froyo" to 0f, "GB" to 20f, "ICS" to 20f, "JB" to 180f, "KitKat" to 260f, "L" to 300f, "M" to 150f)
    private val padding = 10
    private val w = width/datas.size-2*padding
    private var whitePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init{
        whitePaint.color = Color.WHITE

        whitePaint.textSize = 18f
        whitePaint.textAlign = Paint.Align.CENTER

        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//      综合练习
//      练习内容：使用各种 Canvas.drawXXX() 方法画直方图

//      Null Handling in Kotlin => http://blog.omalley.id.au/2013/07/27/null-handling-in-kotlin.html
        width = (canvas?.width ?: 600).minus(2*margin)
//        width = canvas!!.width - 2*margin
        height = width*0.7f
        if ((canvas?.height ?: -1) < height+2*margin){
            height = (canvas?.height ?: -1) - 3*margin
        }

        canvas?.drawLines(floatArrayOf(margin, margin, margin, height+margin, margin, height+margin, width+margin, height+margin), whitePaint)

        datas.entries.forEachIndexed { index, mutableEntry ->
            var rectF = calRectF(mutableEntry.value, index)
            canvas?.drawRect(rectF, paint)

            var centerX = rectF.left + (rectF.right - rectF.left)/2
            canvas?.drawText(mutableEntry.key, centerX, rectF.bottom+20f, whitePaint)
         }

        whitePaint.textSize = 30f
        canvas?.drawText("直方图", canvas?.width/2f, canvas?.height-25f, whitePaint)

    }

    private fun calRectF(h: Float, index: Int) : RectF{
        var left = margin + (2*padding+w)*index + padding
        var right = left + w
        var bottom = height + margin
        var top = height + margin - h
//        Log.d("Debug", "=====left: "+left+"====right: "+right+"====bottom: "+bottom+"===top: "+top+"====")
        return RectF(left, top, right, bottom)
    }

}