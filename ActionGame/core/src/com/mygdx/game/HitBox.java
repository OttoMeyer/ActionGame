package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class HitBox {
    Vector2 location;
    float width, height;
    public HitBox(){
        location = new Vector2();
        width = 0;
        height = 0;
    }

    public HitBox(float width, float height){
        location = new Vector2(0,0);
        this.width = width;
        this.height = height;
    }

    public HitBox(float width, float height, Vector2 location){
        this.location = new Vector2(location);
        this.width = width;
        this.height = height;
    }

    public HitBox(float width, float height, float x, float y){
        this.location = new Vector2(x, y);
        this.width = width;
        this.height = height;
    }

    public void setPosition(Vector2 location){
        this.location = new Vector2(location);
    }

    public void setPosition(float x, float y){
        this.location = new Vector2(x, y);
    }

    public boolean isCross(HitBox body2){
        if(((location.x < body2.location.x && location.x + width > body2.location.x)
                ||(location.x < body2.location.x + body2.width && location.x + width > body2.location.x + body2.width))
                &&((location.y < body2.location.y && location.y + height > body2.location.y)
                ||(location.y < body2.location.y + body2.height && location.y + height > body2.location.y + body2.height))){
            return true;
        }
        else return false;
    }

    public boolean isWillCross(HitBox body2, float x, float y){
        if(((location.x + x < body2.location.x && location.x + width + x > body2.location.x)
                ||(location.x + x < body2.location.x + body2.width && location.x + width + x > body2.location.x + body2.width))
                &&((location.y + y < body2.location.y && location.y + height + y > body2.location.y)
                ||(location.y + y < body2.location.y + body2.height && location.y + height + y > body2.location.y + body2.height))){
            return true;
        }
        else return false;
    }

    public void showHimself(ShapeRenderer shapeRenderer){
        shapeRenderer.rect(location.x, location.y, width, height);
    }

}
