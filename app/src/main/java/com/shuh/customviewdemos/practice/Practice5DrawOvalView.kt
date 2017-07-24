package com.shuh.customviewdemos.practice

import android.content.Context
import android.graphics.Canvas
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.View

/**
 * Created by pc-135 on 2017/7/24.
 */
class Practice5DrawOvalView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        练习内容：使用 canvas.drawOval() 方法画椭圆

    }
}