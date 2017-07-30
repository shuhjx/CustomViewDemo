package com.shuh.customviewdemos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.shuh.customviewdemos.tetris.TetrisView

class TetrisActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var left_arrow: ImageView
    private lateinit var right_arrow: ImageView
    private lateinit var go_on: ImageView
    private lateinit var stop: ImageView
    private lateinit var down_arrow: ImageView
    private lateinit var rotate_right: ImageView
    private lateinit var rotate_left: ImageView
    private lateinit var tetris: TetrisView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tetris)
        initViews()
    }

    private fun initViews(){
        left_arrow = findViewById(R.id.left_arrow)
        right_arrow = findViewById(R.id.right_arrow)
        go_on = findViewById(R.id.go_on)
        stop = findViewById(R.id.stop)
        down_arrow = findViewById(R.id.down_arrow)
        rotate_right = findViewById(R.id.rotate_right)
        rotate_left = findViewById(R.id.rotate_left)
        tetris = findViewById(R.id.tetris)

        left_arrow.setOnClickListener(this)
        right_arrow.setOnClickListener(this)
        go_on.setOnClickListener(this)
        stop.setOnClickListener(this)
        down_arrow.setOnClickListener(this)
        rotate_right.setOnClickListener(this)
        rotate_left.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.left_arrow -> tetris.doAction(TetrisView.Action.MoveLeft)
            R.id.right_arrow -> tetris.doAction(TetrisView.Action.MoveRight)
            R.id.go_on -> tetris.doAction(TetrisView.Action.Continue)
            R.id.stop -> tetris.doAction(TetrisView.Action.Stop)
            R.id.down_arrow -> tetris.doAction(TetrisView.Action.MoveDown)
            R.id.rotate_right -> tetris.doAction(TetrisView.Action.RotateRight)
            R.id.rotate_left -> tetris.doAction(TetrisView.Action.RotateLeft)
        }
    }

}
