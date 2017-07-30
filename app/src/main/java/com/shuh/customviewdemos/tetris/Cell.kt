package com.shuh.customviewdemos.tetris

import android.graphics.Bitmap

/**
 * Created by shu on 2017/7/30.
 */
class Cell{
    var col: Int
    var row: Int
    var bitmap: Bitmap
    constructor(col: Int,
                row: Int,
                bitmap: Bitmap){
        this.col = col + 4
        this.row = row - 4
        this.bitmap = bitmap
    }

    fun moveDown() = row++
    fun moveRight() = col++
    fun moveLeft() = col--
}