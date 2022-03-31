package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


import java.security.Key;

public class Player {
    Vector2 location;
    Vector2 speed;
    float acceleration;
    float maxSpeed;
    Sprite spritePlayer;
    Weapon weapon;
    public Player(float x, float y, float speed){
        this.location = new Vector2(x,y);
        this.speed = new Vector2(speed,0);
        this.acceleration = 40;
        this.maxSpeed = 400;
        spritePlayer = new Sprite(new Texture("Player2.png"));
        weapon = new Weapon();
    }
    public void update(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) speed.add(-acceleration,0);;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) speed.add(acceleration,0);;
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) speed.add(0,-acceleration);;
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) speed.add(0,acceleration);;
        location.x += speed.x * Gdx.graphics.getDeltaTime();
        location.y += speed.y * Gdx.graphics.getDeltaTime();
        speed.x += -speed.x * 0.1f;
        speed.y += -speed.y * 0.1f;
    }
    public void draw(SpriteBatch batch){
        spritePlayer.setPosition(location.x,location.y);
        spritePlayer.draw(batch);
    }
    public void makeHit(Entity entity, Vector3 mouse, SpriteBatch batch){
        weapon.spriteHit.setColor(Color.WHITE);
        weapon.spriteHit.setPosition(weapon.location.x, weapon.location.y);
        weapon.spriteHit.setRotation(weapon.angle);

        weapon.spriteHit.draw(batch);
        double R = Math.pow((location.x - entity.location.x),2) + Math.pow((location.y - entity.location.y),2);
        float eng = new Vector2(mouse.x - location.x, mouse.y - location.y).angleDeg();
        float engEn = new Vector2(entity.location.x - location.x, entity.location.y - location.y).angleDeg();
        if (R < Math.pow(64,2) && ((engEn > eng - 45 && engEn < eng + 45)
                || (engEn - 360 > eng - 45 && engEn < eng + 45) || (engEn > eng - 45 && engEn + 360 < eng + 45))) {
            entity.sprite.setColor(Color.RED);
            entity.HP -= 25;
            System.out.println(eng);
        }
    }
}
