package com.github.yyyank.flurry_of_blows.actor

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.github.yyyank.flurry_of_blows.callback.Starter
import java.util.concurrent.atomic.AtomicInteger


/**
 * Created by yy_yank on 2016/10/21.
 */
class PowerGauge (val skin : Skin) : Image(skin, "powergauge"), Starter {

    override var callback: Runnable? = null

    private val power = AtomicInteger(0)

    init {
        rotation = 90f
    }

    override fun start() {
    }
}