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
class TimeOut(skin: Skin, styleName: String) : Image(skin, styleName) , Starter {

    override var callback: Runnable? = null

    init {
        isVisible = false
    }

    override fun start() {
        val duration = 1f
        color.a = 0f
        isVisible = true
        val fadeIn = Actions.fadeIn(duration)
        val invisible = Actions.visible(false)
        val delay = Actions.delay(1f, Actions.sequence(invisible, resolveDispatch(this), resolveCallback(callback)))
        addAction(Actions.sequence(fadeIn, delay))
    }
}