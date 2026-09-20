package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

public class Toad extends ApplicationAdapter {
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;
	private Sprite sprite;
	private Texture texture;
	private OrthographicCamera camera;
	private static final float VIRTUAL_WIDTH = 600;
	float x, y, cote;
	float angle;
	
	
	public void create() {
		shapeRenderer = new ShapeRenderer();	
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("toad.jpg"));
		sprite = new Sprite(texture);
		sprite.setSize(sprite.getWidth()/3, sprite.getHeight()/3);
		x=100;
		y=10;
		cote=120;
		sprite.setCenter(x+cote/2, y+cote/2);
		angle=2;
	}

	@Override
	public void resize(int width, int height) {
		float virtualHeight = VIRTUAL_WIDTH * (float) height / width;
		camera.setToOrtho(false, VIRTUAL_WIDTH, virtualHeight);
	}


	private void drawGrid (ShapeRenderer sh, float x, float y, float cote) {
		float x1;
		for(int i=1; i<=3; i++) {
			x1=x;
			for (int j=1; j<=3; j++) {
			sh.rect(x1, y, cote, cote);		
			x1+=cote;
			}
			y+=cote;
		}
	}
	
	private void handlePosition1(Sprite sp, float x, float y, float cote){
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT))
			sp.translateX(cote);
		if(Gdx.input.isKeyJustPressed(Keys.LEFT))
			sp.translateX(-cote);
		if(Gdx.input.isKeyJustPressed(Keys.UP))
			sp.translateY(cote);
		if(Gdx.input.isKeyJustPressed(Keys.DOWN))
			sp.translateY(-cote);
		sp.setX(MathUtils.clamp(sp.getX(), x+cote/2-sp.getWidth()/2, x+2.5f*cote-sp.getWidth()/2));
		sp.setY(MathUtils.clamp(sp.getY(), y+cote/2-sp.getHeight()/2, y+2.5f*cote-sp.getHeight()/2));
	}
	
	private void handlePosition2(Sprite sp, float x, float y, float cote){
		float x0=x+cote/2;
		float y0=y+cote/2;
		if(Gdx.input.isKeyPressed(Keys.NUMPAD_1))
			sp.setCenter(x0, y0);		
		if(Gdx.input.isKeyPressed(Keys.NUMPAD_2))
			sp.setCenter(x0+cote, y0);			
		if(Gdx.input.isKeyPressed(Keys.NUMPAD_3))
			sp.setCenter(x0+2*cote, y0);
		
		if(Gdx.input.isKeyPressed(Keys.NUMPAD_4))
			sp.setCenter(x0, y0+cote);
		if(Gdx.input.isKeyPressed(Keys.NUMPAD_5))
			sp.setCenter(x0+cote, y0+cote);
		if(Gdx.input.isKeyPressed(Keys.NUMPAD_6))
			sp.setCenter(x0+2*cote, y0+cote);
		
		if(Gdx.input.isKeyPressed(Keys.NUMPAD_7))
			sp.setCenter(x0, y0+2*cote);
		if(Gdx.input.isKeyPressed(Keys.NUMPAD_8))
			sp.setCenter(x0+cote, y0+2*cote);
		if(Gdx.input.isKeyPressed(Keys.NUMPAD_9))
			sp.setCenter(x0+2*cote, y0+2*cote);
	}

	public void handleCam() {
		if(Gdx.input.isKeyPressed(Keys.T))
			camera.rotate(angle);
		if(Gdx.input.isKeyPressed(Keys.W))
			camera.rotate(-angle);
		if(Gdx.input.isKeyPressed(Keys.I))
			camera.zoom+=0.02f;
		if(Gdx.input.isKeyPressed(Keys.O))
			camera.zoom-=0.02f;		
		camera.zoom=MathUtils.clamp(camera.zoom, 0.1f, 5);
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		batch.setProjectionMatrix(camera.combined);
	}
	
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.handlePosition1(sprite, x, y, cote);
		this.handlePosition2(sprite, x, y, cote);	
		this.handleCam();
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.BLACK);
		this.drawGrid(shapeRenderer, x, y, cote);
		shapeRenderer.end();
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}

	public void dispose() {
		shapeRenderer.dispose();
		batch.dispose();
		texture.dispose();
	}
}
