package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.LiveEntity;

import java.util.ArrayList;

public class Totem extends LiveEntity {
    float stateTime;
    public Totem(Vector2 location, int HP){
        super(location, new Vector2(0,0), HP);
        sprite = new Sprite(new Texture("Totem.png"));
    }

    public void update(ArrayList<Еnemy> entities){
        stateTime += Gdx.graphics.getDeltaTime();
        if(stateTime > 10){
            stateTime = 0;
            System.out.println(stateTime);
            entities.add(new Еnemy(location));
        }
    }
}
