package com.mygdx.tutorial;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danie on 20/09/2018.
 */

public class OrthogonalTiledMapRenderWithSprites extends OrthogonalTiledMapRenderer{
    private Sprite sprite;
    private List<Sprite> sprites;
    private int drawSpritesAfterLayer = 1; // change for layer level

    public OrthogonalTiledMapRenderWithSprites(TiledMap map){
        super(map);
        sprites = new ArrayList<Sprite>();
    }
    public void addSprite(Sprite sprite){
        sprites.add(sprite);
    }

    @Override
    public void render(){
        beginRender();
        int currentLayer = 1;
        for (MapLayer layer : map.getLayers()){
            if(layer.isVisible()){
                if (layer instanceof TiledMapTileLayer){
                    renderTileLayer((TiledMapTileLayer)layer);
                    currentLayer++;
                    if(currentLayer == drawSpritesAfterLayer){
                        for (Sprite sprite : sprites)
                            sprite.draw(this.getBatch());
                    }
                }else{
                    for (MapObject object : layer.getObjects()){
                        renderObject(object);
                    }

                }
            }
        }
        endRender();
    }

}
