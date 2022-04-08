package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SimpleEntity {
    public Vector2 location;
    public Sprite sprite;
    public SimpleEntity(Vector2 location){
        this.location = new Vector2(location);
        sprite = new Sprite(new Texture("Nothing.png"));
    }

    public void draw(SpriteBatch batch){
        sprite.setPosition(location.x,location.y);
        sprite.draw(batch);
    }
}


