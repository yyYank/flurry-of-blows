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
import com.github.yyyank.flurry_of_blows.FlurryOfBlowsGame
import com.github.yyyank.flurry_of_blows.actor.*
import com.github.yyyank.flurry_of_blows.callback.CallbackRouter
import com.github.yyyank.flurry_of_blows.domain.FlurryOfBlowsScoreIntent
import com.github.yyyank.flurry_of_blows.domain.Position
import com.github.yyyank.flurry_of_blows.frameByFrame
import com.github.yyyank.flurry_of_blows.moveTo
import com.github.yyyank.flurry_of_blows.register

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
        stage.register(Image(skin, "fobBackground"), Position(0f, 0f))
        stage.register(countDown, Position(stage.width - countDown.width, stage.height - countDown.height))
        stage.register(ready, Position((stage.width - ready.width) / 2f, (stage.height - ready.height) / 2f))
        stage.register(go, Position((stage.width - go.width) / 2f, (stage.height - go.height) / 2f))
        stage.register(timeout, Position((stage.width - timeout.width) / 2f, (stage.height - timeout.height) / 2f))
        stage.register(powerGauge, Position(stage.width, powerGauge.height))
        stage.register(countUp, Position(stage.width - countUp.width, stage.height - countUp.height - countUp.height))
        val animation = Animation(0.1f, frameByFrame("fob/fob-button1.png", "fob/fob-button2.png", "fob/fob-button3.png", "fob/fob-button4.png"))
        val animated = AnimatedImage(animation)
        val buttonClickFunction = object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                countUp.counted()
            }
        }
        stage.register(animated, Position(animated.width / 4f, 0f), buttonClickFunction)
        countDown.callback = Runnable {
            animated.removeListener(buttonClickFunction)
            moveTo(ProcessingFobScreen(game, am, FlurryOfBlowsScoreIntent(countUp.counter.toInt())), game, stage)
        }
        with(CallbackRouter) {
            initialize()
            defineRoot(ready, go)
            defineRoot(go, countDown)
            defineRoot(countDown, timeout)
        }
        CallbackRouter.start()
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