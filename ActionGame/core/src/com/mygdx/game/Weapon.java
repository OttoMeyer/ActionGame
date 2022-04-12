package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Weapon {
    String idName;
    Vector2 location;
    float angle;
    Sprite spriteWeapon;
    Sprite spriteHit;
    //Skill skillOne;
    //Skill skillTwo;
    public Weapon(){
        idName = "Null";
        spriteWeapon = new Sprite();
        spriteHit = new Sprite();
    }
    public void chengeWeapon(String idName){
        this.idName = idName;
        switch(idName){
            case "Null":
                spriteWeapon = new Sprite();
                spriteHit = new Sprite(new Texture("Hit.png"));
                break;
            case "Sword":
                spriteWeapon = new Sprite(new Texture("Sword.png"));
                spriteHit = new Sprite(new Texture("Hit.png"));
                //skillOne = new Skill("Slash");
                break;
            case "Hummer":
                spriteWeapon = new Sprite(new Texture("Hummer.png"));
                spriteHit = new Sprite(new Texture("Hit.png"));
                break;
            case "Spear":
                spriteWeapon = new Sprite(new Texture("Spear.png"));
                spriteHit = new Sprite(new Texture("Hit.png"));
                break;


        }
    }
    public void update(Vector2 location, Vector3 mouse){
        this.location = new Vector2(location);
        angle = new Vector2(mouse.x-location.x,mouse.y-location.y).angleDeg();
        this.location.add(new Vector2(mouse.x-location.x,mouse.y-location.y).nor().scl(16));
    }
    public void draw(SpriteBatch batch){
        spriteWeapon.setRotation(angle-45);
        spriteWeapon.setPosition(location.x,location.y);
        spriteWeapon.draw(batch);

    }
}
