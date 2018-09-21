package com.mygdx.tutorial;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;

public class tutorialGame extends ApplicationAdapter implements InputProcessor{
	Texture img;
    TiledMap tiledMap;
	OrthographicCamera camera;
	//TiledMapRenderer tiledMapRenderer;
    OrthogonalTiledMapRenderWithSprites tiledMapRenderer;
   // SpriteBatch sb;
    Texture texture;
    Sprite sprite;
    Vector3 pos;


	@Override
	public void create () {
        float w = (float) (Gdx.graphics.getWidth() *2.6); //*2.6 for simulated phone doesn't work on mine though
        float h = (float) (Gdx.graphics.getHeight() *2.6);

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        pos = new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() /2, 0);
        camera.update();

        texture = new Texture(Gdx.files.internal("pik.png"));
        sprite = new Sprite(texture);


        //tiledMap = new TmxMapLoader().load("myCrappyMap.tmx");
        tiledMap = new TmxMapLoader().load("tafe_basement.tmx");
        //tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMapRenderer = new OrthogonalTiledMapRenderWithSprites(tiledMap);
        tiledMapRenderer.addSprite(sprite);
        Gdx.input.setInputProcessor(this);

        //sb = new SpriteBatch();


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
//		sb.begin();
//		sprite.draw(sb);
//		sb.end();

        //this renders the sprite on touch and follows smoothly
        if(Gdx.input.isTouched()) {
           pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(pos);
        }
        sprite.setPosition(pos.x,pos.y);
	}


	@Override
    public boolean keyDown(int keycode){
	    return false;
    }

    @Override
    public boolean keyUp(int keycode){
        if(keycode == Input.Keys.LEFT)
            camera.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            camera.translate(32,0);
        if(keycode == Input.Keys.UP)
            camera.translate(0,-32);
        if(keycode == Input.Keys.DOWN)
            camera.translate(0,32);
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        return false;
    }
    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //this renders the sprite on touch bit not smoothly
//        Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
//        Vector3 position = camera.unproject(clickCoordinates);
//        sprite.setPosition(position.x,position.y);
//        return true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
	}
}
