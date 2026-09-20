package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class ExamTp extends ApplicationAdapter {
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;
	private Sprite sprite;
	private Texture texture;
	private OrthographicCamera camera;
	private static final float VIRTUAL_WIDTH = 600;
	private static final float Width_caree = 90;
	private static float pas;
	
	public void create() {
		shapeRenderer = new ShapeRenderer();	
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("mario.jpg"));
		sprite = new Sprite(texture);
		sprite.setPosition(Width_caree, 0);
	}

	@Override
	public void resize(int width, int height) {
		float virtualHeight = VIRTUAL_WIDTH * (float) height / width;
		camera.setToOrtho(false, VIRTUAL_WIDTH, virtualHeight);
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
	}

	private void handle_Input() {
		pas=10;
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			sprite.setFlip(false, false);
			sprite.translateX(pas);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			sprite.setFlip(true, false);
			sprite.translateX(-pas);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			
			sprite.setPosition(sprite.getX(), camera.viewportHeight-sprite.getHeight());
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			sprite.setPosition(sprite.getX(), 0);
		}
		
		sprite.setX(MathUtils.clamp(sprite.getX(), Width_caree, camera.viewportWidth-Width_caree-sprite.getWidth()));
	}
	
	private void drawCarreRed(float x, float y, float w) {
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(x, y, w, camera.viewportHeight/5);
		shapeRenderer.end();
	}
	
	private void drawCarreBlack(float x, float y, float w) {
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(x, y, w, camera.viewportHeight/5);
		shapeRenderer.end();
	}
	private void drawLigne(float x, float y, float w) {
			drawCarreRed(x, y, w);
			drawCarreBlack(x, y+camera.viewportHeight/5f, w);
			drawCarreRed(x, y+(camera.viewportHeight/5f*2), w);
			drawCarreBlack(x, y+(camera.viewportHeight/5f*3), w);
			drawCarreRed(x, y+(camera.viewportHeight/5f*4), w);
	}
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.drawLigne(0, 0, Width_caree);
		this.drawLigne(camera.viewportWidth-Width_caree, 0, Width_caree);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		handle_Input();
	}
	
	
	
	public void dispose() {
		shapeRenderer.dispose();
		batch.dispose();
		texture.dispose();
	}
}
