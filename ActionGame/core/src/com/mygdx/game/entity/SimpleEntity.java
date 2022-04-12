package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.HitBox;

public class SimpleEntity {
    public Vector2 location;
    public Sprite sprite;
    public HitBox hitBox;
    public SimpleEntity(Vector2 location){
        this.location = new Vector2(location);
        sprite = new Sprite(new Texture("Nothing.png"));
        hitBox = new HitBox(sprite.getWidth(), sprite.getHeight(), location);
    }

    public SimpleEntity(Vector2 location, String nameSprite){
        this.location = new Vector2(location);
        sprite = new Sprite(new Texture(nameSprite));
        hitBox = new HitBox(sprite.getWidth(), sprite.getHeight(), location);
    }

    public void draw(SpriteBatch batch){
        sprite.setPosition(location.x,location.y);
        sprite.draw(batch);
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer){
        sprite.setPosition(location.x,location.y);
        sprite.draw(batch);
    }

    public void drawHitBox(ShapeRenderer shapeRenderer){
        hitBox.showHimself(shapeRenderer);
    }
}


