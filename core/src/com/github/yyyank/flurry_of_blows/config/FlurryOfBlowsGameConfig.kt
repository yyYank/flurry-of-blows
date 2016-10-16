package com.github.yyyank.flurry_of_blows.config

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * Created by yy_yank on 2016/10/16.
 */
data class FlurryOfBlowsGameConfig(
        val worldWidth: Float = 540f,
        val worldHeight: Float = 960f,
        val viewport : FitViewport = FitViewport(worldWidth, worldHeight),
        var stage : Stage = Stage(viewport)) {
}