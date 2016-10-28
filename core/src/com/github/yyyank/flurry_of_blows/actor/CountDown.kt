package com.github.yyyank.flurry_of_blows.actor

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.github.yyyank.flurry_of_blows.callback.Starter
import com.github.yyyank.flurry_of_blows.callback.resolveCallback
import com.github.yyyank.flurry_of_blows.callback.resolveDispatch
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by yy_yank on 2016/10/21.
 */
class CountDown(skin: Skin, countStart: Int = 10) : Label(countStart.toString(), skin, "counter"), Starter {

    override var callback: Runnable? = null
    val counter: AtomicInteger = AtomicInteger(countStart)
    var count: Int = countStart
        set(value) {
            field = value
            setText(String.format("%02d", value))
        }

    init {
        setText(String.format("%02d", countStart))
        setColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a)
    }


    override fun start() {
        val decrement = Actions.run(Runnable {
            count = counter.decrementAndGet()
        })
        val delay = Actions.delay(1f, decrement)
        val repeat = Actions.repeat(count, delay)
        addAction(Actions.sequence(repeat, resolveDispatch(this), resolveCallback(callback)))
    }
}
