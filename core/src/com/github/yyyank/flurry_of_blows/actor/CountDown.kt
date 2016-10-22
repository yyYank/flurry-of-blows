package com.github.yyyank.flurry_of_blows.actor

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by yy_yank on 2016/10/21.
 */
class CountDown(skin: Skin, countStart : Int = 10, val callback: Runnable) : Label("00", skin, "counter") {

    val counter : AtomicInteger = AtomicInteger(countStart)
    var count : Int = countStart
    set(value) {
        field = value
        setText(String.format("%02d", value))
    }

    init {
        setText(String.format("%02d", countStart))
    }


    fun start() {
        val decrement = Actions.run(Runnable {
            count = counter.decrementAndGet()
        })
        val delay = Actions.delay(1f, decrement)
        val repeat = Actions.repeat(count, delay)
        val run = callback.let { Actions.run(it) }
        addAction(Actions.sequence(repeat, run))
    }
}
