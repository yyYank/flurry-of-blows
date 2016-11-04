package com.github.yyyank.flurry_of_blows.config

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import java.util.concurrent.TimeUnit

/**
 * Created by yy_yank on 2016/10/17.
 */
sealed class AssetAndSkinInitializer() {

    abstract fun initialize(skin: Skin, am: AssetManager)

    val params = TextureLoader.TextureParameter().let {
        it.minFilter = Texture.TextureFilter.Linear
        it.magFilter = Texture.TextureFilter.Linear
        it
    }

    object TitleScreenSkin : AssetAndSkinInitializer() {
        override fun initialize(skin: Skin, am: AssetManager) {
            val titleBackgroundAsset = textureDesc("title/background.png", params);
            val titleStartUpAsset = textureDesc("title/startup.png", params);
            val titleStartDownAsset = textureDesc("title/startdown.png", params);
            val assets = listOf(
                    "titleBackground" to titleBackgroundAsset,
                    "titleStartUp" to titleStartUpAsset,
                    "titleStartDown" to titleStartDownAsset
            )

            assets.forEach {
                val (name, asset) = it
                am.load(asset)
                while (!am.update()) {
                    TimeUnit.MILLISECONDS.sleep(10)
                }
                am.finishLoading()
                skin.add(name, am.get(asset), Texture::class.java)
            }

            Button.ButtonStyle(skin.getDrawable("titleStartUp"), skin.getDrawable("titleStartDown"), null).let { skin.add("titleStart", it, Button.ButtonStyle::class.java) }
        }
    }

    object FlurryOfBlowsScreenSkin : AssetAndSkinInitializer() {
        override fun initialize(skin: Skin, am: AssetManager) {
            val fontParams = BitmapFontLoader.BitmapFontParameter().let {
                it.minFilter = Texture.TextureFilter.Linear
                it.magFilter = Texture.TextureFilter.Linear
                it
            }
            val fontAsset = AssetDescriptor<BitmapFont>("mplus1m.fnt", BitmapFont::class.java, fontParams)
            am.load(fontAsset)
            am.finishLoading()
            skin.add("default", am.get(fontAsset), BitmapFont::class.java)
            val font = skin.getFont("default")
            Label.LabelStyle(font, Color.WHITE).let { skin.add("counter", it, Label.LabelStyle::class.java) }
            Label.LabelStyle(font, Color.WHITE).let { skin.add("countup", it, Label.LabelStyle::class.java) }


            val backgroundAsset = textureDesc("fob/background.png", params);
            val gameReadyAsset = textureDesc("fob/ready.png", params)
            val gameGoAsset = textureDesc("fob/go.png", params)
            val timeoutAsset = textureDesc("fob/finished.png", params)
            val button1 = textureDesc("fob/fob-button1.png", params)
            val button2 = textureDesc("fob/fob-button2.png", params)
            val button3 = textureDesc("fob/fob-button3.png", params)
            val button4 = textureDesc("fob/fob-button4.png", params)
            val powergauge = textureDesc("fob/powergauge.png", params)
            am.load(backgroundAsset)
            am.load(gameReadyAsset)
            am.load(gameGoAsset)
            am.load(timeoutAsset)
            am.load(button1)
            am.load(button2)
            am.load(button3)
            am.load(button4)
            am.load(powergauge)
            am.finishLoading()
            skin.add("fobBackground", am.get(backgroundAsset), Texture::class.java)
            skin.add("ready", am.get(gameReadyAsset), Texture::class.java)
            skin.add("go", am.get(gameGoAsset), Texture::class.java)
            skin.add("timeout", am.get(timeoutAsset), Texture::class.java)
            skin.add("button1", am.get(button1), Texture::class.java)
            skin.add("button2", am.get(button2), Texture::class.java)
            skin.add("button3", am.get(button3), Texture::class.java)
            skin.add("button4", am.get(button4), Texture::class.java)
            skin.add("powergauge", am.get(powergauge), Texture::class.java)
        }
    }

    object ProcessingFobScreenSkin : AssetAndSkinInitializer() {
        override fun initialize(skin: Skin, am: AssetManager) {
        }
    }

    object FobResultScreenSkin : AssetAndSkinInitializer() {
        override fun initialize(skin: Skin, am: AssetManager) {

            val resultRestartUpAsset = textureDesc("result/restartUp.png", params)
            val resultRestartDownAsset = textureDesc("result/restartDown.png", params)
            val resultExitUpAsset = textureDesc("result/exitUp.png", params)
            val resultExitDownAsset = textureDesc("result/exitDown.png", params)
            am.load(resultRestartUpAsset)
            am.load(resultRestartDownAsset)
            am.load(resultExitUpAsset)
            am.load(resultExitDownAsset)
            am.finishLoading()

            skin.add("resultRestartUp", am.get(resultRestartUpAsset), Texture::class.java)
            skin.add("resultRestartDown", am.get(resultRestartDownAsset), Texture::class.java)
            skin.add("resultExitUp", am.get(resultExitUpAsset), Texture::class.java)
            skin.add("resultExitDown", am.get(resultExitDownAsset), Texture::class.java)

            Button.ButtonStyle(skin.getDrawable("resultRestartUp"), skin.getDrawable("resultRestartDown"), null).let { skin.add("resultRestart", it, Button.ButtonStyle::class.java) }
            Button.ButtonStyle(skin.getDrawable("resultExitUp"), skin.getDrawable("resultExitDown"), null).let { skin.add("resultExit", it, Button.ButtonStyle::class.java) }
        }
    }
}

fun textureDesc(s: String, params: TextureLoader.TextureParameter): AssetDescriptor<Texture> = AssetDescriptor(s, Texture::class.java, params)
