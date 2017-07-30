package com.shuh.customviewdemos.tetris

import android.content.Context
import android.graphics.*
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.shuh.customviewdemos.R
import java.util.*


/**
 * Created by shu on 2017/7/30.
 */
class TetrisView : View {
    constructor(context: Context) : super(context){
        startAction()
    }
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        startAction()
    }
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        startAction()
    }

    private var background: Bitmap
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var bSrc: Rect
    private var bDst: RectF? = null
    private var bWidth = -1f
    private var bHeight = -1f
    companion object {
        lateinit var T: Bitmap
        lateinit var I: Bitmap
        lateinit var O: Bitmap
        lateinit var L: Bitmap
        lateinit var J: Bitmap
        lateinit var S: Bitmap
        lateinit var Z: Bitmap
    }

    init{
        background = BitmapFactory.decodeResource(context.resources, R.mipmap.tetris)
        T = BitmapFactory.decodeResource(context.resources, R.mipmap.t)
        I = BitmapFactory.decodeResource(context.resources, R.mipmap.i)
        O = BitmapFactory.decodeResource(context.resources, R.mipmap.o)
        L = BitmapFactory.decodeResource(context.resources, R.mipmap.l)
        J = BitmapFactory.decodeResource(context.resources, R.mipmap.j)
        S = BitmapFactory.decodeResource(context.resources, R.mipmap.s)
        Z = BitmapFactory.decodeResource(context.resources, R.mipmap.z)
        bSrc = Rect(0, 0 ,background.width, background.height)
    }

    private var PROPORTION = 1f
    private var CELL_SIZE = 26f
    private var TRAN_SIZE = 15f
    private val ROWS = 20
    private val COLS = 10
    private var wall  = Array(ROWS){Array<Cell?>(COLS, {null})}
    private var lines: Int = 0
    private var score: Int = 0
    private val SCORES = intArrayOf(0, 1, 5, 25, 50)
    private var tetromino: Tetromino? = null
    private var nextOne: Tetromino? = null
    private var timer: Timer? = null
    private var gameOver = false
    private var pause = false

    /*******游戏画面的绘制 Begin*********/
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        calAndSetBDst(canvas)

        canvas.drawBitmap(background, bSrc, bDst, null)
        canvas.translate(TRAN_SIZE, TRAN_SIZE)
        drawTetromino(canvas)
        drawNextOne(canvas)
        drawScore(canvas, paint)
        drawWall(canvas, paint)
    }

    private fun calAndSetBDst(canvas: Canvas){
        if (bDst == null){
            if(canvas.width <= canvas.height){
                bWidth = canvas.width.toFloat()
                bHeight = background.height * bWidth / background.width
            }else{
                bHeight = canvas.height.toFloat()
                bWidth = background.width * bHeight / background.height
            }
            PROPORTION = bWidth / background.width
            CELL_SIZE *= PROPORTION
            TRAN_SIZE *= PROPORTION
            bDst = RectF(0f, 0f, bWidth, bHeight)
        }
    }

    private fun drawTetromino(canvas: Canvas){
        val cells = tetromino?.cells!!
        for (i in cells.indices) {
            val cell = cells[i]!!
            val x = cell.col * CELL_SIZE - 1
            val y = cell.row * CELL_SIZE - 1
            canvas.drawBitmap(cell.bitmap, Rect(0, 0, cell.bitmap.width, cell.bitmap.height),
                    RectF(x, y, x+CELL_SIZE - 1, y+CELL_SIZE - 1), null)
        }
    }
    private fun drawNextOne(canvas: Canvas){
        val cells = nextOne?.cells!!

        for (i in cells.indices) {
            val cell = cells[i]!!
            val x = cell.col * CELL_SIZE - 1
            val y = cell.row * CELL_SIZE - 1
            canvas.drawBitmap(cell.bitmap, x + 260*PROPORTION, y + 30*PROPORTION, null)
        }
    }
    private fun drawScore(canvas: Canvas, paint: Paint){
        val x = 285f*PROPORTION
        var y = 160f*PROPORTION
        paint.color = Color.parseColor("#607491")// 字体颜色
        paint.typeface = Typeface.MONOSPACE// 设置等宽字体
        paint.isFakeBoldText = true
        paint.textSize = 30f*PROPORTION

        var str = "SCORE:" + score
        canvas.drawText(str, x, y, paint)
        y += 56*PROPORTION
        str = "LINES:" + lines
        canvas.drawText(str, x, y, paint)
        str = "[P]Pause"
        if (gameOver) {
            str = "[S]Start"
        }
        if (pause) {
            str = "[C]Continue"
        }
        y += 56*PROPORTION
        canvas.drawText(str, x, y, paint)
    }
    private fun drawWall(canvas: Canvas, paint: Paint){
        paint.style = Paint.Style.STROKE
        paint.color = Color.WHITE
        for (row in 0..ROWS - 1) {
            val line = wall[row]
            for (col in line.indices) {
                val cell = line[col]
                val x = col * CELL_SIZE
                val y = row * CELL_SIZE
                if (cell == null) {
                    canvas.drawRect(x, y, x+CELL_SIZE, y+CELL_SIZE, paint)
                } else {
                    canvas.drawBitmap(cell.bitmap, Rect(0, 0, cell.bitmap.width, cell.bitmap.height),
                            RectF(x - 1, y - 1, x+CELL_SIZE - 2, y+CELL_SIZE - 2), null)
                }
            }
        }
    }

    private fun repaint() {
        handler?.post { invalidate() }
    }

    /*******游戏画面的绘制 End*********/

    enum class Action{
        MoveLeft, MoveRight, MoveDown, RotateRight, RotateLeft,
        Stop, Start, GameOver, Continue
    }

    fun doAction(action: Action){
        if(gameOver){
            gameOverAction()
        }
        if(pause){
            if(action == Action.Continue){
                continueAction()
            }
            return
        }
        Log.d("Debug", "doAction: "+action)
        when(action){
            Action.MoveLeft -> moveLeftAction()
            Action.MoveRight -> moveRightAction()
            Action.MoveDown -> hardDropAction()
            Action.RotateRight -> rotateRightAction()
            Action.RotateLeft -> rotateLeftAction()
            Action.Stop -> pauseAction()
            Action.Start -> {
                timer?.cancel()
                startAction()
            }
            Action.GameOver -> gameOverAction()
            Action.Continue -> continueAction()
        }
        repaint()
    }

    /*******控制游戏的状态 Begin*********/
    private fun startAction(){
        gameOver = false
        pause = false
        clearWall()
        score = 0
        lines = 0
        tetromino = Tetromino.randomTetromino()
        nextOne = Tetromino.randomTetromino()
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                softDropAction()
                repaint()
            }
        }, 800L, 800L)
    }

    private fun continueAction() {
        pause = false
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                softDropAction()
                repaint()
            }
        }, 800, 800)
    }

    private fun gameOverAction() {
        gameOver = true
        timer?.cancel()
        for (i in 0..ROWS - 1) {
            Arrays.fill(wall[i], T)
        }
    }

    private fun pauseAction() {
        pause = true
        timer?.cancel()
    }
    /*******控制游戏的状态 End*********/
    /*******控制方块的运动 Begin*********/
    private fun rotateRightAction() {
        tetromino?.rotateRight()
        if (outOfBound() || coincide()) {
            tetromino?.rotateLeft()
        }
    }

    private fun rotateLeftAction() {
        tetromino?.rotateLeft()
        if (outOfBound() || coincide()) {
            tetromino?.rotateRight()
        }
    }

    private fun moveRightAction() {
        tetromino?.moveRight()
        if (outOfBound() || coincide()) {
            tetromino?.moveLeft()
        }
    }

    private fun moveLeftAction() {
        tetromino?.moveLeft()
        if (outOfBound() || coincide()) {
            tetromino?.moveRight()
        }
    }

    private fun softDropAction() {
        if (tetrominoCanDrop()) {
            tetromino?.softDrop()
        } else {
            tetrominoLandToWall()
            destroyLines()
            if (checkGameOver()) {
                return
            }
            tetromino = nextOne
            nextOne = Tetromino.randomTetromino()
        }
    }

    private fun hardDropAction() {
        while (tetrominoCanDrop()) {
            tetromino?.softDrop()
        }
        tetrominoLandToWall()
        destroyLines()
        if (checkGameOver()) {
            return
        }
        tetromino = nextOne
        nextOne = Tetromino.randomTetromino()

    }
    /*******控制方块的运动 End*********/

    private fun clearWall() {
        for (i in 0..ROWS - 1) {
            Arrays.fill(wall[i], null)
        }
    }

    private fun checkGameOver(): Boolean {
        return wall[0][4] != null
    }

    private fun tetrominoCanDrop(): Boolean {
        val cells = tetromino?.cells!!
        for (col in cells.indices) {
            val cell = cells[col]!!
            val y = cell.row
            if (y == ROWS - 1) {
                return false
            }
        }
        for (col in cells.indices) {
            val cell = cells[col]!!
            val x = cell.col
            val y = cell.row
            if (wall[y + 1][x] != null) {
                return false
            }
        }

        return true
    }

    private fun tetrominoLandToWall() {
        val cells = tetromino?.cells!!
        for (i in cells.indices) {
            val cell = cells[i]!!
            val x = cell.col
            val y = cell.row
            if(x >= 0 && y >= 0)
                wall[y][x] = cell
        }

    }

    private fun destroyLines() {
        var lines = 0
        for (row in 0..ROWS - 1) {
            if (fullCells(row)) {
                deleteRow(row)
                lines++
            }
        }
        this.lines += lines
        this.score += SCORES[lines]
    }

    private fun fullCells(row: Int): Boolean {
        val line = wall[row]
        for (i in line.indices) {
            if (line[i] == null) {
                return false
            }
        }
        return true
    }

    private fun deleteRow(row: Int) {
        for (i in row downTo 1) {
            System.arraycopy(wall[i - 1], 0, wall[i], 0, COLS.toInt())
        }
        Arrays.fill(wall[0], null)
    }

    private fun outOfBound(): Boolean {
        val cells = tetromino?.cells!!
        for (i in cells.indices) {
            val cell = cells[i]!!
            val col = cell.col
            if (col < 0 || col >= COLS) {
                return true
            }
        }
        return false
    }

    private fun coincide(): Boolean {
        val cells = tetromino?.cells!!
        for (cell in cells) {
            val col = cell!!.col
            val row = cell.row
            if (col < 0 || col >= COLS || row < 0 || row >= ROWS || wall[row][col] != null) {
                return true
            }
        }
        return false
    }

}