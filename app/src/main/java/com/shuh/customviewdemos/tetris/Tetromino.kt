package com.shuh.customviewdemos.tetris

import java.util.*


/**
 * Created by shu on 2017/7/30.
 */
abstract class Tetromino{
    val cells = Array<Cell?>(4, {null})
    protected lateinit var states: Array<State>

    companion object {
        fun randomTetromino(): Tetromino? {
            when (Random().nextInt(7)) {
                0 -> return T()
                1 -> return O()
                2 -> return L()
                3 -> return J()
                4 -> return S()
                5 -> return Z()
                6 -> return I()
            }
            return null
        }
    }

    fun softDrop() {
        for (i in 0..cells.size - 1) {
            cells[i]?.moveDown()
        }
    }

    fun moveRight() {
        for (i in 0..cells.size - 1) {
            cells[i]?.moveRight()
        }
    }

    fun moveLeft() {
        for (i in 0..cells.size - 1) {
            cells[i]?.moveLeft()
        }
    }

    private var index = 10000
    fun rotateRight() {
        index++
        val s = states[index % states.size]
        val o = cells[0]!!
        cells[1]?.col = o.col + s.col1
        cells[1]?.row = o.row + s.row1
        cells[2]?.col = o.col + s.col2
        cells[2]?.row = o.row + s.row2
        cells[3]?.col = o.col + s.col3
        cells[3]?.row = o.row + s.row3
    }

    fun rotateLeft() {
        index--
        val s = states[index % states.size]
        val o = cells[0]!!
        cells[1]?.col = o.col + s.col1
        cells[1]?.row = o.row + s.row1
        cells[2]?.col = o.col + s.col2
        cells[2]?.row = o.row + s.row2
        cells[3]?.col = o.col + s.col3
        cells[3]?.row = o.row + s.row3
    }

    inner class State(var col0: Int, var row0: Int, var col1: Int, var row1: Int, var col2: Int, var row2: Int, var col3: Int, var row3: Int)
}

class T : Tetromino() {
    init {
        cells[0] = Cell(0, 4, TetrisView.T)
        cells[1] = Cell(0, 3, TetrisView.T)
        cells[2] = Cell(0, 5, TetrisView.T)
        cells[3] = Cell(1, 4, TetrisView.T)
        states = arrayOf(State(0, 0, -1, 0, 1, 0, 0, 1),
                State(0, 0, 0, -1, 0, 1, -1, 0),
                State(0, 0, 1, 0, -1, 0, 0, -1),
                State(0, 0, 0, 1, 0, -1, 1, 0))
    }
}

internal class I : Tetromino() {
    init {
        cells[0] = Cell(0, 4, TetrisView.I)
        cells[1] = Cell(0, 3, TetrisView.I)
        cells[2] = Cell(0, 5, TetrisView.I)
        cells[3] = Cell(0, 6, TetrisView.I)
        states = arrayOf(State(0, 0, -1, 0, 1, 0, 2, 0),
                State(0, 0, 0, -1, 0, 1, 0, 2))
    }
}

internal class L : Tetromino() {
    init {
        cells[0] = Cell(0, 4, TetrisView.L)
        cells[1] = Cell(0, 3, TetrisView.L)
        cells[2] = Cell(0, 5, TetrisView.L)
        cells[3] = Cell(1, 3, TetrisView.L)
        states = arrayOf(State(0, 0, -1, 0, 1, 0, -1, 1),
                State(0, 0, 0, -1, 0, 1, -1, -1),
                State(0, 0, 1, 0, -1, 0, 1, -1),
                State(0, 0, 0, 1, 0, -1, 1, 1),
                State(0, 0, -1, 0, 1, 0, -1, 1))
    }
}

internal class J : Tetromino() {
    init {
        cells[0] = Cell(0, 4, TetrisView.J)
        cells[1] = Cell(0, 3, TetrisView.J)
        cells[2] = Cell(0, 5, TetrisView.J)
        cells[3] = Cell(1, 5, TetrisView.J)
        states = arrayOf(State(0, 0, -1, 0, 1, 0, 1, 1),
                State(0, 0, 0, -1, 0, 1, 1, -1),
                State(0, 0, 1, 0, -1, 0, 1, 1),
                State(0, 0, 0, 1, 0, -1, -1, 1),
                State(0, 0, -1, 0, 1, 0, -1, -1))
    }
}

internal class S : Tetromino() {
    init {
        cells[0] = Cell(0, 4, TetrisView.S)
        cells[1] = Cell(0, 5, TetrisView.S)
        cells[2] = Cell(1, 3, TetrisView.S)
        cells[3] = Cell(1, 4, TetrisView.S)
        states = arrayOf(State(0, 0, 1, 0, -1, 1, 0, 1),
                State(0, 0, 0, 1, -1, -1, -1, 0))
    }
}

internal class Z : Tetromino() {
    init {
        cells[0] = Cell(1, 4, TetrisView.Z)
        cells[1] = Cell(0, 3, TetrisView.Z)
        cells[2] = Cell(0, 4, TetrisView.Z)
        cells[3] = Cell(1, 5, TetrisView.Z)
        states = arrayOf(State(0, 0, -1, -1, 0, -1, 1, 0),
                State(0, 0, 1, -1, 1, 0, 0, 1))
    }
}

internal class O : Tetromino() {
    init {
        cells[0] = Cell(0, 4, TetrisView.O)
        cells[1] = Cell(0, 5, TetrisView.O)
        cells[2] = Cell(1, 4, TetrisView.O)
        cells[3] = Cell(1, 5, TetrisView.O)
        states = arrayOf(State(0, 0, 1, 0, 0, 1, 1, 1),
                State(0, 0, 1, 0, 0, 1, 1, 1))
    }
}