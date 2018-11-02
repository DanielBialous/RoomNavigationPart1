package com.mygdx.tutorial;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class tutorialGame extends ApplicationAdapter implements InputProcessor, GestureDetector.GestureListener {
    TiledMap tiledMap;
	OrthographicCamera camera;
	//TiledMapRenderer tiledMapRenderer;
    OrthogonalTiledMapRenderWithSprites tiledMapRenderer;
    //SpriteBatch sb;
    Texture texture;
    Sprite sprite;
    Vector3 pos;


    GestureDetector detect;



	@Override
	public void create () {
        float w = (float) (Gdx.graphics.getWidth()); //*2.6 for simulated phone doesn't work on mine though
        float h = (float) (Gdx.graphics.getHeight());
        double camZoom = 1;



        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.zoom = (float) camZoom;
        pos = new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() /2, 0);
        camera.update();

        texture = new Texture(Gdx.files.internal("pik.png"));
        sprite = new Sprite(texture);


        //tiledMap = new TmxMapLoader().load("myCrappyMap.tmx");
        //tiledMap = new TmxMapLoader().load("basement_39_22.tmx");
        tiledMap = new  TmxMapLoader().load("ground_Level_small.tmx");
        //tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMapRenderer = new OrthogonalTiledMapRenderWithSprites(tiledMap);
        tiledMapRenderer.addSprite(sprite);
        //Gdx.input.setInputProcessor(this);


        detect  = new GestureDetector(this);
        Gdx.input.setInputProcessor(detect);
        //sb = new SpriteBatch();


	}


	private Vector2 lastTouch = new Vector2();

	public void touchControl(){
        //this renders the sprite on touch and follows smoothly

    }


	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
//		sb.begin();
//		sprite.draw(sb);
//		sb.end();


//        touchControl();

        if(Gdx.input.isTouched()) {
            pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(pos);
        }
        sprite.setPosition(pos.x,pos.y);
        camera.position.set(pos.x ,pos.y ,0); // this sets the new cam pos when you touch the screen
        camera.update();

        //-------- boundary box for the camera against the map --------///
        float camX = camera.position.x;
        float camY = camera.position.y;

//        float borderWidth = 3944; // total allowable width
//        float boaderHight = 2219; //total allowable hight

        float borderWidth = 2110;
        float boaderHight = 1249;

        Vector2 camMin = new Vector2(camera.viewportWidth, camera.viewportHeight);
        camMin.scl(camera.zoom/2);
        Vector2 camMax = new Vector2(borderWidth, boaderHight);
        camMax.sub(camMin);

        camX = Math.min(camMax.x, Math.max(camX, camMin.x));
        camY = Math.min(camMax.y, Math.max(camY, camMin.y));

        camera.position.set(camX, camY, camera.position.z);

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
//        camera.position.set(sprite.getX(),sprite.getY(),0);
//        camera.update();
//        sprite.setPosition(position.x,position.y);

//        lastTouch.set(screenX,screenY);
        //return true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

//        Vector2 newTouch = new Vector2(screenX,screenY);
//        Vector2 delta = newTouch.cpy().sub(lastTouch);
//        lastTouch = newTouch;
//
//        camera.position.set(newTouch.x,newTouch.y, 0);
//        camera.update();

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

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if(count > 1 )
            camera.zoom = 1;


        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
	    // TODO put code in here for the selection of room on the map dropdown room info or select destination
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {

        float zoomFactor = distance/200;

           camera.zoom = zoomFactor;

        camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 1.2f); //clamping the min and max on zoom

        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {

        float deltaX = pointer2.x - pointer1.x;
        float deltaY = pointer2.y - pointer1.y;

        float angle = (float)Math.atan2((double)deltaY,(double)deltaX) * MathUtils.radiansToDegrees;
        angle += 90f;

        if(angle < 0)
            angle = 360f - (-angle);

        camera.rotate(-angle/200);

        return true;
    }

    @Override
    public void pinchStop() {

    }


}
