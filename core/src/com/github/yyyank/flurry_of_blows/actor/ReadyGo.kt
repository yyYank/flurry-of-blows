package com.github.yyyank.flurry_of_blows.actor

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.github.yyyank.flurry_of_blows.callback.CallbackRouter
import com.github.yyyank.flurry_of_blows.callback.Starter
import com.github.yyyank.flurry_of_blows.callback.resolveCallback
import com.github.yyyank.flurry_of_blows.callback.resolveDispatch

/**
 * Created by yy_yank on 2016/10/21.
 */
class Ready(val skin: Skin) : Image(skin, "ready"), Starter {


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

class Go(val skin: Skin) : Image(skin, "go"), Starter {

    override var callback: Runnable? = null

    init {
        isVisible = false
    }

    override fun start() {
        val duration = 1f
        isVisible = true
        val fadeOut = Actions.fadeOut(duration)
        val invisible = Actions.visible(false)
        addAction(Actions.sequence(Actions.parallel(fadeOut, resolveDispatch(this), resolveCallback(callback)), invisible))
    }
}