package com.github.yyyank.flurry_of_blows.actor

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.utils.Array
import com.github.yyyank.flurry_of_blows.Logger
import com.github.yyyank.flurry_of_blows.callback.Starter
import com.github.yyyank.flurry_of_blows.domain.MovablePosition
import com.github.yyyank.flurry_of_blows.domain.Position
import java.util.concurrent.atomic.AtomicInteger


/**
 * Created by yy_yank on 2016/10/21.
 */
class PowerGauge(val skin: Skin, val s : Stage) : Image(skin, "powergauge"), Starter {

    override var callback: Runnable? = null
    val texture = PowerGaugeTexture(s)

    init {
        rotation = 90f
    }

    override fun start() {

    }
}


class PowerGaugeTexture(stage :Stage) : Actor() {

    val mutablePosition = MovablePosition(100f, 0f)

    init {
    }

    fun increment(stage : Stage) {
        this.debug()
        val skin = Skin()
        mutablePosition.move(0f, 10f)

        val pixmap = Pixmap(500, 500, Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.RED)
        pixmap.fill()
        pixmap.drawRectangle(0, 0, 64, 64)
        val tex = Texture(pixmap)

        stage.batch.begin()
        stage.batch.draw(tex, mutablePosition.x, mutablePosition.y)
        stage.batch.end()
    }
}