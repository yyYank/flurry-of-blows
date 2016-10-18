package com.github.yyyank.flurry_of_blows

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions

/**
 * Created by yy_yank on 2016/10/16.
 */
fun move(game: FlurryOfBlowsGame, stage: Stage, screen: ScreenAdapter) {
    val fadeOut = Actions.fadeOut(0.5f)
    val toGameScreen = Actions.run(Runnable {
        game.screen = screen
    })
    stage.addAction(Actions.sequence(fadeOut, toGameScreen))
}



