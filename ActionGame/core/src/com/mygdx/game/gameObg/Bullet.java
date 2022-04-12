package com.mygdx.game.gameObg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    Vector2 location;
    Vector2 speed;
    Sprite spriteBullet;
    boolean destroy;
    Sound sound;
    public Bullet(float beginX, float beginY, float endX, float endY){
        location = new Vector2(beginX,beginY);
        speed = new Vector2(endX-beginX,endY-beginY);
        speed.nor();
        speed.scl(1000);
        spriteBullet = new Sprite(new Texture("arrow2.png"));
        sound = Gdx.audio.newSound(Gdx.files.internal("Bum.wav"));
        sound.play();
    }

    public void update(){
        location.x += speed.x * Gdx.graphics.getDeltaTime();
        location.y += speed.y * Gdx.graphics.getDeltaTime();
        if(location.x<0 || location.x>Gdx.graphics.getWidth() || location.y<0 || location.y>Gdx.graphics.getWidth()){
            destroy = true;
        }
    }
    public void draw(SpriteBatch batch){
        spriteBullet.setRotation(speed.angleDeg()-45);
        spriteBullet.setPosition(location.x,location.y);
        spriteBullet.draw(batch);

    }
}
