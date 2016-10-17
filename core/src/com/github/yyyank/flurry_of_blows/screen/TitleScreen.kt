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
import com.github.yyyank.flurry_of_blows.FlurryOfBlowsGame
import com.github.yyyank.flurry_of_blows.config.SkinInitializer

/**
 * タイトル画面
 * Created by yy_yank on 2016/10/16.
 */
class TitleScreen(val game : FlurryOfBlowsGame, val am : AssetManager) : ScreenAdapter() {

    val stage : Stage = game.config.stage
    val skin : Skin

    init {
        skin = SkinInitializer.initialize(am)

        Image(skin, "titleBackground").let {
            it.setPosition(0f, 0f)
            stage.addActor(it)
        }

        Button(skin, "titleStart").let {
            it.setPosition(0f, it.height)
            stage.addActor(it)
            it.addListener(object : ClickListener() {
                override fun clicked(event: InputEvent, x: Float, y: Float) {
                    val fadeOut = Actions.fadeOut(0.5f)
                    val toGameScreen = Actions.run(Runnable {
                        game.screen = FlurryOfBlowsScreen()
                    })
                    stage.addAction(Actions.sequence(fadeOut, toGameScreen))
                }
            })
        }



        val toTransparent = Actions.alpha(0f)
        val fadeIn = Actions.fadeIn(0.5f)
        stage.addAction(Actions.sequence(toTransparent, fadeIn))
    }

    override fun render(delta: Float) {
        super.render(delta)

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height)
    }

    override fun show() {
        Gdx.input.inputProcessor = stage
    }

    override fun hide() {
        dispose()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
        stage.dispose()
    }
}