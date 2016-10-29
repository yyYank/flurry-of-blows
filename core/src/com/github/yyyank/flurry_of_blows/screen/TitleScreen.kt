package com.github.yyyank.flurry_of_blows.screen

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.github.yyyank.flurry_of_blows.FlurryOfBlowsGame
import com.github.yyyank.flurry_of_blows.Logger
import com.github.yyyank.flurry_of_blows.domain.Position
import com.github.yyyank.flurry_of_blows.moveTo
import com.github.yyyank.flurry_of_blows.register

/**
 * タイトル画面
 * Created by yy_yank on 2016/10/16.
 */
class TitleScreen(val game: FlurryOfBlowsGame, val am: AssetManager) : ScreenAdapter() {

    val stage: Stage
    val skin: Skin = game.config.skin

    init {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Logger.debug("${this.javaClass.name} init")
        stage = Stage(game.config.viewport)
        stage.register(Image(skin, "titleBackground"), Position(0f, 0f))
        val button = Button(skin, "titleStart")
        stage.register(button, Position(0f, button.height),
                object : ClickListener() {
                    override fun clicked(event: InputEvent, x: Float, y: Float) {
                        moveTo(FlurryOfBlowsScreen(game, am), game, stage)
                    }
                }
        )
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
    }

    override fun hide() {
        Logger.debug("${this.javaClass.name} hide")
        dispose()
    }

    override fun pause() {
        Logger.debug("${this.javaClass.name} pause")
        super.pause()
    }

    override fun resume() {
        Logger.debug("${this.javaClass.name} resume")
        super.resume()
    }

    override fun dispose() {
        Logger.debug("${this.javaClass.name} dispose")
        stage.dispose()
    }
}