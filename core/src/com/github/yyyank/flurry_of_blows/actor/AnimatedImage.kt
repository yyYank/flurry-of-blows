package com.github.yyyank.flurry_of_blows.actor

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by yy_yank on 2016/10/24.
 * http://stackoverflow.com/questions/16059578/libgdx-is-there-an-actor-that-is-animated
 */
class AnimatedImage (val animation : Animation) : Image(animation.getKeyFrame(0f))  {

    private var stateTime : Float = 0f;
    val counter : AtomicInteger = AtomicInteger(0)
    override fun act(delta : Float) {
        val d = drawable as TextureRegionDrawable
        stateTime+=delta
        d.region = animation?.getKeyFrame(stateTime, true)
        super.act(delta);
    }
}