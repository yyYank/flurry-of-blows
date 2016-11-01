package com.github.yyyank.flurry_of_blows.domain

/**
 * Created by yy_yank on 2016/10/18.
 */
class Position(val x: Float, val y: Float) {
    companion object  {
        val NONE = Position(0f, 0f)
    }
}