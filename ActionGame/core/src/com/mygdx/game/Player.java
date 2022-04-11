package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.entity.LiveEntity;


import java.util.ArrayList;

public class Player extends LiveEntity {
    float acceleration;
    float maxSpeed;
    Weapon weapon;
    final float DIG = 100;
    public Player(float x, float y, float speed){
        super(new Vector2(x,y), new Vector2(0,0), 100, "Player2.png");
        this.speed = new Vector2(0,0);
        this.acceleration = 40/DIG;
        this.maxSpeed = 400/DIG;
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
        super.update();
        //location.x += speed.x * Gdx.graphics.getDeltaTime();
        //location.y += speed.y * Gdx.graphics.getDeltaTime();
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


    public void makeHit(Еnemy entity, Vector3 mouse, SpriteBatch batch){
        weapon.skillOne.useSkill(entity, mouse, batch);
    }

}
