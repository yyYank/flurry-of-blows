package com.github.yyyank.flurry_of_blows.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.github.yyyank.flurry_of_blows.FlurryOfBlowsGame
import com.github.yyyank.flurry_of_blows.Logger
import com.github.yyyank.flurry_of_blows.actor.Text
import com.github.yyyank.flurry_of_blows.domain.Position
import com.github.yyyank.flurry_of_blows.domain.PreviousScreenIntent
import com.github.yyyank.flurry_of_blows.domain.ProcessingFobScreenIntent
import com.github.yyyank.flurry_of_blows.moveTo
import com.github.yyyank.flurry_of_blows.register

/**
 * 連打後のプロセス画面
 * Created by yy_yank on 2016/10/16.
 */
class ProcessingFobScreen(val game: FlurryOfBlowsGame, val am: AssetManager, val score: PreviousScreenIntent<Int>) : ScreenAdapter() {

    val stage: Stage
    val skin: Skin = game.config.skin

    init {
        stage = Stage(game.config.viewport)
        val textActor = Text(
                "審議中・・・・・・",40
                , Position(100f, 800f)
                ,Color.WHITE)
        stage.register(textActor)
    }


    override fun render(delta: Float) {
        super.render(delta)
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        Logger.debug("${this.javaClass.name} resize")
        stage.viewport.update(width, height)
    }

    override fun show() {
        Logger.debug("${this.javaClass.name} show")
        Gdx.input.inputProcessor = stage
        moveTo(FobResultScreen(game, am, ProcessingFobScreenIntent(score.receive())), game, stage)
    }

    override fun hide() {
        Logger.debug("${this.javaClass.name} hide")
        dispose()
    }

    override fun pause() {
        Logger.debug("${this.javaClass.name} pause")
    }

    override fun resume() {
        Logger.debug("${this.javaClass.name} resume")
    }

    override fun dispose() {
        Logger.debug("${this.javaClass.name} dispose")
        stage.dispose()
    }
}