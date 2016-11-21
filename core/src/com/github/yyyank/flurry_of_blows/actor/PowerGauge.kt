package com.github.yyyank.flurry_of_blows.actor

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.github.yyyank.flurry_of_blows.callback.Starter


/**
 * Created by yy_yank on 2016/10/21.
 */
class PowerGauge(val skin: Skin, val s : Stage) : Group(), Starter {


    override var callback: Runnable? = null
    val powerImage = Image(skin, "powergauge")
    val texImage : Image
    init {
        powerImage.rotation = 90f
        val pix = Pixmap(60, 10, Pixmap.Format.RGBA8888)
        pix.setColor(Color.RED)
        pix.fill()
        pix.drawRectangle(60, 10, 64, 64)
        val tex = Texture(pix)
        val region = TextureRegion(tex, 80, 10, 60, 10)
        texImage = Image(region)
        addActor(powerImage)
        addActor(texImage)
    }
    fun increment(stage : Stage) {
        texImage.setSize(texImage.width, texImage.height + 5)
    }

    override fun setPosition(x: Float, y: Float) {
        powerImage.setPosition(x, y)
        texImage.setPosition(x - 105f, y + 42f)
    }

    override fun start() {
    }
}

