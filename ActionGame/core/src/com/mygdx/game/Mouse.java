package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Mouse {

    public Mouse(){

    }
    public void draw(ShapeRenderer shape){
        shape.setColor(Color.ROYAL);
        shape.rect(Gdx.input.getX()-5, Gdx.graphics.getHeight()-Gdx.input.getY()-5, 10,10);
    }
}
