package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class MyGdxGame extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture soleil;
	private Sprite sprite_soleil;
	private static final float VIRTUAL_WIDTH = 600;
	
	
	
	
	public void create() {
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		soleil = new Texture(Gdx.files.internal("soleil.jpg"));
		sprite_soleil = new Sprite(soleil);
		
	}

	public void resize(int width, int height) {
		float virtualHeight = VIRTUAL_WIDTH * (float) height / width;
		camera.setToOrtho(false, VIRTUAL_WIDTH, virtualHeight);
		
	}
	private void handle_Input() {
		if(Gdx.input.isKeyPressed(Input.Keys.R)) {
			sprite_soleil.scale(-0.5f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.G)) {
			sprite_soleil.scale(0.5f);
		}
		
		sprite_soleil.setScale(MathUtils.clamp(sprite_soleil.getScaleX(), 0.4f, 6f));
	}
	
	private void draw() {
		batch.begin();
		sprite_soleil.draw(batch);
		sprite_soleil.setPosition(camera.viewportWidth/2f-sprite_soleil.getWidth()/2f, camera.viewportHeight/2f-sprite_soleil.getHeight()/2f);
		batch.end();
		
	}

	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		handle_Input();
		this.draw();
		
	}
	
	

	public void dispose() {
		
	}

}
