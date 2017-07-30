package com.shuh.customviewdemos.practice

import android.content.Context
import android.graphics.*
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by pc-135 on 2017/7/24.
 */
class Practice11PieChartView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val datas = linkedMapOf("Lollipop" to 120f, "Marshmallow" to 60f, "Froyo" to 5f,
            "Gingerbread" to 10f, "Ice Cream Sandwich" to 10f, "Jelly Bean" to 45f, "KitKat" to 110f)
    private val colors = arrayOf(Color.parseColor("#F44336"), Color.parseColor("#FFC107"),
            Color.argb(0,0,0,0), Color.parseColor("#9C27B0"),
            Color.parseColor("#9E9E9E"), Color.parseColor("#009688"),
            Color.parseColor("#2196F3"))
    private var whitePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val padding = 2f
    private val totalAngle = 360f - datas.size*padding

    init{
        whitePaint.color = Color.WHITE

        whitePaint.textSize = 18f
        whitePaint.style = Paint.Style.STROKE
        whitePaint.textAlign = Paint.Align.CENTER

        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL_AND_STROKE
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//      综合练习
//      练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        val radius = canvas.height-50-100
        val rectF = RectF(100f, 50f, 100f + radius, 50f + radius)

//        canvas.drawRect(rectF, whitePaint)
        var total = 0f
        datas.values.forEach{v -> total += v }
//        Log.d("Debug", "total: "+total)
        var startPos = 180f
        datas.entries.forEachIndexed { index, mutableEntry ->
            val sweepAngle = mutableEntry.value*totalAngle/total
//            Log.d("Debug", "startPos: "+startPos+", sweepAngle: "+sweepAngle+", name: "+mutableEntry.key+", val: "+mutableEntry.value)
            paint.color = colors[index%colors.size]

            if(index == 0)
                canvas.translate(-10f, -10f)

            canvas.drawArc(rectF, startPos, sweepAngle, true, paint)

            if(index == 0)
                canvas.translate(10f, 10f)

            startPos += sweepAngle + padding
        }


    }
}