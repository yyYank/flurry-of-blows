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
		Float worldWidth = 540f;
		Float worldHeight = 960f;
		FitViewport viewport =  new FitViewport(worldWidth, worldHeight);
		Stage stage = new Stage(viewport);
		return new FlurryOfBlowsGameConfig(worldWidth, worldHeight, viewport, stage);
	}



	@Override
	public void create () {
		config = createConfig();
		SkinInitializer.INSTANCE.initialize(am);
		setScreen(new TitleScreen(this, am));
	}

	@Override
	public void render () {
	}
	
	@Override
	public void dispose () {
	}
}
