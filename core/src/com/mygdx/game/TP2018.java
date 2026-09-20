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

public class TP2018 extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture car;
	private Sprite sprite;
	private Texture street;
	private Sprite sprite_street;
	private static final float VIRTUAL_WIDTH = 600;
	
	
	
	
	public void create() {
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		car = new Texture(Gdx.files.internal("car-removebg-preview.png"));
		sprite = new Sprite(car);
		street = new Texture(Gdx.files.internal("street.jpg"));
		sprite_street = new Sprite(street);
		sprite_street.setPosition(0, 0);
		sprite.setPosition(100, 0);
	}

	public void resize(int width, int height) {
		float virtualHeight = VIRTUAL_WIDTH * (float) height / width;
		camera.setToOrtho(false, VIRTUAL_WIDTH, virtualHeight);
		
	}
	private void handle_Input() {
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			sprite.setFlip(false, false);
			if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
				sprite_street.translateX(-1);
			}
			else {
				sprite_street.translateX(-10);
			}
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			sprite.setFlip(true, false);
			if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
				sprite_street.translateX(1);
			}
			else {
				sprite_street.translateX(10);
			}
		}
		sprite_street.setX(MathUtils.clamp(sprite_street.getX(), camera.viewportWidth-sprite_street.getWidth(),0));
	}
	
	private void draw() {
		batch.begin();
		sprite_street.draw(batch);
		sprite.draw(batch);
		
		sprite_street.setSize(camera.viewportWidth*2, camera.viewportHeight/5);
			
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
