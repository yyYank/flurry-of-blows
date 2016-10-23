package com.github.yyyank.flurry_of_blows.actor

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.github.yyyank.flurry_of_blows.callback.CallbackRouter
import com.github.yyyank.flurry_of_blows.callback.Starter
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by yy_yank on 2016/10/21.
 */
class CountDown(skin: Skin, countStart : Int = 10) : Label(countStart.toString(), skin, "counter"), Starter {

    override var callback: Runnable? = null
    val counter : AtomicInteger = AtomicInteger(countStart)
    var count : Int = countStart
    set(value) {
        field = value
        setText(String.format("%02d", value))
    }

    init {
        setText(String.format("%02d", countStart))
    }


    override fun start() {
        val decrement = Actions.run(Runnable {
            count = counter.decrementAndGet()
        })
        val delay = Actions.delay(1f, decrement)
        val repeat = Actions.repeat(count, delay)
        callback = Runnable {
            CallbackRouter.next(this).forEach { Starter::start }
        }
        val run = callback.let { Actions.run(it) }
        addAction(Actions.sequence(repeat, run))
    }
}
