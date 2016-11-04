package com.github.yyyank.flurry_of_blows.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.github.yyyank.flurry_of_blows.*
import com.github.yyyank.flurry_of_blows.actor.*
import com.github.yyyank.flurry_of_blows.callback.CallbackRouter
import com.github.yyyank.flurry_of_blows.domain.FlurryOfBlowsScoreIntent
import com.github.yyyank.flurry_of_blows.domain.Position

/**
 * 連打画面
 * Created by yy_yank on 2016/10/16.
 */
class FlurryOfBlowsScreen(val game: FlurryOfBlowsGame, val am: AssetManager) : ScreenAdapter() {

    val stage: Stage
    val skin: Skin = game.config.skin

    init {
        stage = Stage(game.config.viewport, SpriteBatch())
        // http://stackoverflow.com/questions/13863138/what-is-the-simplest-way-to-make-image-touchable-in-libgdx
        Gdx.input.inputProcessor = stage
        val ready = Ready(skin)
        val go = Go(skin)
        val countDown = CountDown(skin)
        val timeout = TimeOut(skin, "timeout")
        val powerGauge = PowerGauge(skin)
        val countUp = CountUp(skin)
        val animation = Animation(0.1f, frameByFrame("fob/fob-button1.png", "fob/fob-button2.png", "fob/fob-button3.png", "fob/fob-button4.png"))
        val animated = AnimatedImage(animation)
        val buttonClickFunction = object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                countUp.counted()
            }
        }


        countDown.callback =  Runnable {
            animated.removeListener(buttonClickFunction)
            moveTo(ProcessingFobScreen(game, am, FlurryOfBlowsScoreIntent(countUp.counter.toInt())), game, stage)
        }


        stage.run{
            register(Image(skin, "fobBackground"), Position(0f, 0f))
            register(countDown, Position(stage.width - countDown.width, stage.height - countDown.height))
            register(ready, Position((stage.width - ready.width) / 2f, (stage.height - ready.height) / 2f))
            register(go, Position((stage.width - go.width) / 2f, (stage.height - go.height) / 2f))
            register(timeout, Position((stage.width - timeout.width) / 2f, (stage.height - timeout.height) / 2f))
            register(powerGauge, Position(stage.width, powerGauge.height))
            register(countUp, Position(stage.width - countUp.width, stage.height - countUp.height - countUp.height))
            register(animated, Position(animated.width / 4f, 0f), buttonClickFunction)
        }

        CallbackRouter.apply {
            initialize()
            defineRoot(ready, go)
            defineRoot(go, countDown)
            defineRoot(countDown, timeout)
        }.start()
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
        super.show()
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