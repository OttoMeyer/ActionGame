package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


import java.util.ArrayList;

public class Player {
    HitBox hitBox;
    Vector2 location;
    Vector2 speed;
    float acceleration;
    float maxSpeed;
    Sprite spritePlayer;
    Weapon weapon;
    public Player(float x, float y, float speed){
        this.location = new Vector2(x,y);
        hitBox = new HitBox(16,16, location);
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
        hitBox.setPosition(location);
    }

    public void update(ArrayList<HitBox> hitBoxes){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) speed.add(-acceleration,0);;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) speed.add(acceleration,0);;
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) speed.add(0,-acceleration);;
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) speed.add(0,acceleration);;
        if(isCrossAll(hitBoxes)){
            speed.setZero();
        }
        location.x += speed.x * Gdx.graphics.getDeltaTime();
        location.y += speed.y * Gdx.graphics.getDeltaTime();
        speed.x += -speed.x * 0.1f;
        speed.y += -speed.y * 0.1f;
        hitBox.setPosition(location);
        //System.out.println(isCrossAll(hitBoxes));

    }

    private boolean isCrossAll(ArrayList<HitBox> hitBoxes){
        for(HitBox h : hitBoxes){
            if(this.hitBox.isWillCross(h, speed.x * Gdx.graphics.getDeltaTime(), speed.y * Gdx.graphics.getDeltaTime())){
                return true;
            }
        }
        return false;
    }

    public void draw(SpriteBatch batch){
        spritePlayer.setPosition(location.x,location.y);
        spritePlayer.draw(batch);
    }
    public void makeHit(Еnemy entity, Vector3 mouse, SpriteBatch batch){
        weapon.skillOne.useSkill(entity, mouse, batch);
    }

    public void test(Еnemy entity){
        System.out.println(hitBox.isCross(entity.hitBox));
    }
}
