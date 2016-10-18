package com.github.yyyank.flurry_of_blows;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.yyyank.flurry_of_blows.config.FlurryOfBlowsGameConfig;
import com.github.yyyank.flurry_of_blows.config.SkinInitializer;
import com.github.yyyank.flurry_of_blows.screen.TitleScreen;

public class FlurryOfBlowsGame extends Game {


    public FlurryOfBlowsGameConfig config;
    public AssetManager am = new AssetManager();

    private FlurryOfBlowsGameConfig createConfig() {
        Float gameWidth = 540f;
        Float gameHeight = 960f;
        FitViewport viewport = new FitViewport(gameWidth, gameHeight);
        Stage stage = new Stage(viewport);
        return new FlurryOfBlowsGameConfig(gameWidth, gameHeight, viewport, stage, SkinInitializer.INSTANCE.initialize(am));
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
