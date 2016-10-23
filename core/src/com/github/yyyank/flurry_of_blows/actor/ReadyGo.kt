package com.github.yyyank.flurry_of_blows.actor

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.github.yyyank.flurry_of_blows.callback.CallbackRouter
import com.github.yyyank.flurry_of_blows.callback.Starter
import kotlin.properties.Delegates

/**
 * Created by yy_yank on 2016/10/21.
 */
class Ready(val skin : Skin) : Image(skin, "ready") , Starter {


    override var callback: Runnable? = null
    override fun start() {
        val scale = 0.8f
        val duration = 1f
        val scaleTo = Actions.scaleTo(scale, scale, duration)
        val moveBy = Actions.moveBy((width - (width * scale)) / 2f, (height - (height * scale)) / 2f, duration)
        val invisible = Actions.visible(false)
        callback = Runnable {
            CallbackRouter.next(this).forEach(Starter::start)
        }
        val run = Actions.run(callback)
        addAction(Actions.sequence(Actions.parallel(scaleTo, moveBy), invisible, run))
    }
}
class Go(val skin : Skin) : Image(skin, "go") , Starter {

    override var callback: Runnable? = null
    init {
        isVisible = false
    }
    override fun start() {
        val duration = 1f
        isVisible = true
        val fadeOut = Actions.fadeOut(duration)
        callback = Runnable {
            CallbackRouter.next(this).forEach(Starter::start)
        }
        val run = callback?.let{ Actions.run(it) }
        val invisible = Actions.visible(false)
        addAction(Actions.sequence(Actions.parallel(fadeOut, run), invisible))
    }
}