package com.github.yyyank.flurry_of_blows;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.yyyank.flurry_of_blows.config.FlurryOfBlowsGameConfig;
import com.github.yyyank.flurry_of_blows.config.AssetAndSkinInitializer;
import com.github.yyyank.flurry_of_blows.screen.TitleScreen;

public class FlurryOfBlowsGame extends Game {


    public FlurryOfBlowsGameConfig config;
    public AssetManager am = new AssetManager();
    public Skin skin = new Skin();

    private FlurryOfBlowsGameConfig createConfig() {
        AssetAndSkinInitializer.TitleScreenSkin.INSTANCE.initialize(skin, am);
        AssetAndSkinInitializer.FlurryOfBlowsScreenSkin.INSTANCE.initialize(skin, am);
        AssetAndSkinInitializer.FobResultScreenSkin.INSTANCE.initialize(skin, am);
        AssetAndSkinInitializer.ProcessingFobScreenSkin.INSTANCE.initialize(skin, am);
        Float gameWidth = 540f;
        Float gameHeight = 960f;
        FitViewport viewport = new FitViewport(gameWidth, gameHeight);
        return new FlurryOfBlowsGameConfig(gameWidth, gameHeight, viewport, skin);
    }


    @Override
    public void create() {
        if (am.update()) {
            config = createConfig();
            setScreen(new TitleScreen(this, am));
        }
        System.out.println(this.getClass().getSimpleName() + ":create");
    }
}
