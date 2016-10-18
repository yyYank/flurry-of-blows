package com.github.yyyank.flurry_of_blows.config

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import java.util.concurrent.TimeUnit

/**
 * Created by yy_yank on 2016/10/17.
 */
sealed class AssetAndSkinInitializer() {

    abstract fun initialize(skin : Skin, am: AssetManager)

    object TitleScreenSkin  : AssetAndSkinInitializer() {
        override fun initialize(skin : Skin, am: AssetManager) {

            val fontParams = BitmapFontLoader.BitmapFontParameter().let {
                it.minFilter = Texture.TextureFilter.Linear
                it.magFilter = Texture.TextureFilter.Linear
                it
            }
            val params = TextureLoader.TextureParameter().let {
                it.minFilter = Texture.TextureFilter.Linear
                it.magFilter = Texture.TextureFilter.Linear
                it
            }

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
        override fun initialize(skin : Skin, am: AssetManager) {
        }
    }

    object ProcessingFobScreenSkin : AssetAndSkinInitializer() {
        override fun initialize(skin: Skin, am: AssetManager) {
        }
    }

    object FobResultScreenSkin : AssetAndSkinInitializer() {
        override fun initialize(skin: Skin, am: AssetManager) {
        }
    }
}

fun textureDesc(s: String, params: TextureLoader.TextureParameter): AssetDescriptor<Texture> = AssetDescriptor(s, Texture::class.java, params)
