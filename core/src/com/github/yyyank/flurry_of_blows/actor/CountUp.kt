package com.github.yyyank.flurry_of_blows.actor

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by yy_yank on 2016/10/21.
 */
class CountUp(skin: Skin) : Label("000", skin, "countup") {


    val counter: AtomicInteger = AtomicInteger(0)

    init {
        setColor(Color.MAGENTA.r, Color.MAGENTA.g, Color.MAGENTA.b, Color.MAGENTA.a)
    }

    fun counted() {
        setText(String.format("%03d", counter.incrementAndGet()))
    }
}
