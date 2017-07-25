package com.shuh.customviewdemos.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.View

/**
 * Created by pc-135 on 2017/7/24.
 */
class Practice9DrawPathView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        练习内容：使用 canvas.drawPath() 方法画心形

        var paint = Paint(Paint.ANTI_ALIAS_FLAG)

        paint.style = Paint.Style.FILL_AND_STROKE

        var path = Path()
        var rectF1 = RectF(200f, 100f, 400f, 300f)
        path.addArc(rectF1, -225f, 225f)

        var rectF2 = RectF(400f, 100f, 600f, 300f)
        path.arcTo(rectF2, -180f, 225f, false)

        path.lineTo(400f, 442f)
        canvas?.drawPath(path, paint)

//        paint.style = Paint.Style.STROKE
//        canvas?.drawRect(rectF1, paint)
//        canvas?.drawRect(rectF2, paint)

    }
}