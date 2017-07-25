package com.shuh.customviewdemos.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.View

/**
 * Created by pc-135 on 2017/7/24.
 */
class Practice2DrawCircleView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val radius = 110f
        val padding = 20f
//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        var paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL_AND_STROKE //既画线又填充
        canvas?.drawCircle(radius+padding, radius+padding, radius, paint)

        paint.style = Paint.Style.STROKE //只画线
        canvas?.drawCircle(radius*3+2*padding, radius+padding, radius, paint)

        paint.style = Paint.Style.FILL //只填充
        paint.color = Color.parseColor("#00ff00")
        canvas?.drawCircle(radius+padding, radius*3+2*padding, radius, paint)

        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
        paint.strokeWidth = 25f
        canvas?.drawCircle(radius*3+2*padding, radius*3+2*padding, radius, paint)
    }
}