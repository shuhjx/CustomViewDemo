package com.shuh.customviewdemos.practice

import android.content.Context
import android.graphics.*
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.View

/**
 * Created by pc-135 on 2017/7/24.
 */
class Practice11PieChartView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val datas = hashMapOf("Lollipop" to 120f, "Marshmallow" to 60f, "Froyo" to 5f,
            "Gingerbread" to 10f, "Ice Cream Sandwich" to 10f, "Jelly Bean" to 45f, "KitKat" to 110f)

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
//      练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        var rectF = RectF()




    }
}