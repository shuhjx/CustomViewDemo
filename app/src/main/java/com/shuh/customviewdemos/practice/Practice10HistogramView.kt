package com.shuh.customviewdemos.practice

import android.content.Context
import android.graphics.Canvas
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


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

    }

}