package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.HitBox;

public class LiveEntity extends SimpleEntity {

    public HitBox hitBox;
    public int HP;
    public Vector2 speed;

    public LiveEntity(Vector2 location, Vector2 speed, int HP) {
        super(location);
        this.speed = new Vector2(speed);
        this.HP = HP;
        hitBox = new HitBox(16, 16, this.location);
        sprite = new Sprite(new Texture("Nothing.png"));
    }

    public void update(){
        location.x += speed.x * 10 * Gdx.graphics.getDeltaTime();
        location.y += speed.y * 10 * Gdx.graphics.getDeltaTime();
        hitBox.setPosition(location);
    }
}
