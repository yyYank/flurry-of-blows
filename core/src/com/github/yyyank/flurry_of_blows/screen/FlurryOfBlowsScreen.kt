package com.github.yyyank.flurry_of_blows.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.github.yyyank.flurry_of_blows.FlurryOfBlowsGame
import com.github.yyyank.flurry_of_blows.domain.Position
import com.github.yyyank.flurry_of_blows.register

/**
 * 連打画面
 * Created by yy_yank on 2016/10/16.
 */
class FlurryOfBlowsScreen(val game: FlurryOfBlowsGame, val am: AssetManager) : ScreenAdapter() {

    val stage: Stage
    val skin: Skin = game.config.skin

    init {
        val viewport = FitViewport(game.config.gameWidth, game.config.gameHeight)
        stage = Stage(viewport)
        println("${this.javaClass.name} : init")
        stage.register(Image(skin, "titleBackground"), Position(0f, 0f))
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
        super.show()
    }

    override fun hide() {
        println("${this.javaClass.name} hide")
        dispose()
    }

    override fun pause() {
        println("${this.javaClass.name} pause")
        super.pause()
    }

    override fun resume() {
        println("${this.javaClass.name} resume")
        super.resume()
    }

    override fun dispose() {
        println("${this.javaClass.name} dispose")
        stage.dispose()
    }
}