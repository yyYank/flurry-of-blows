package com.github.yyyank.flurry_of_blows.domain

/**
 * Created by yy_yank on 2016/10/29.
 */
interface PreviousScreenIntent<T : Any> {
    fun receive(): T
}

class FlurryOfBlowsScoreIntent(private val score: Int) : PreviousScreenIntent<Int> {
    override fun receive(): Int = score
}

