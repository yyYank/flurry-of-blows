package com.github.yyyank.flurry_of_blows.config

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * Created by yy_yank on 2016/10/16.
 */
data class FlurryOfBlowsGameConfig(
        val gameWidth: Float = 540f,
        val gameHeight: Float = 960f,
        val viewport: FitViewport = FitViewport(gameWidth, gameHeight),
        val skin: Skin) {
}