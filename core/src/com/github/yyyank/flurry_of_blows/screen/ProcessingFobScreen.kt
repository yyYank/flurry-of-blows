package com.github.yyyank.flurry_of_blows.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.github.yyyank.flurry_of_blows.FlurryOfBlowsGame
import com.github.yyyank.flurry_of_blows.domain.PreviousScreenIntent
import com.github.yyyank.flurry_of_blows.moveTo

/**
 * 連打後のプロセス画面
 * Created by yy_yank on 2016/10/16.
 */
class ProcessingFobScreen(val game: FlurryOfBlowsGame, val am: AssetManager, score: PreviousScreenIntent<Int>) : ScreenAdapter() {

    val stage: Stage
    val skin: Skin = game.config.skin

    init {
        stage = Stage(game.config.viewport)
    }


    override fun render(delta: Float) {
        super.render(delta)
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        println("${this.javaClass.name} resize")
        stage.viewport.update(width, height)
    }

    override fun show() {
        println("${this.javaClass.name} show")
        Gdx.input.inputProcessor = stage
        moveTo(FobResultScreen(game, am), game, stage)
    }

    override fun hide() {
        println("${this.javaClass.name} hide")
        dispose()
    }

    override fun pause() {
        println("${this.javaClass.name} pause")
    }

    override fun resume() {
        println("${this.javaClass.name} resume")
    }

    override fun dispose() {
        println("${this.javaClass.name} dispose")
        stage.dispose()
    }
}