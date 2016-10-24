package com.github.yyyank.flurry_of_blows.callback

import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.actions.Actions

/**
 * Created by yy_yank on 2016/10/24.
 */
fun resolveCallback(callback : Runnable?) : Action = callback?.let{ Actions.run(it) } ?: Actions.run(Runnable{})

fun resolveDispatch(starter : Starter) : Action = Runnable {
    CallbackRouter.next(starter).forEach(Starter::start)
}.let{
    Actions.run(it)
}