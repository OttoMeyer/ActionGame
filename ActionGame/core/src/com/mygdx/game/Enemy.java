package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.LiveEntity;

public class Enemy extends LiveEntity {
    private static int FRAME_COLS = 2, FRAME_ROWS = 1;
    Vector2 target;
    float acceleration;
    float maxSpeed;
    TextureRegion[] animationFrames;
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    float stateTime;

    public Enemy(Vector2 location){
        super(location, new Vector2(0,0), 100);
        this.target = new Vector2(0,0);
        speed = new Vector2(10,10);
        this.acceleration = 40;
        this.maxSpeed = 400;
        sprite = new Sprite(new Texture("AcuZavr.png"));
        сustomizeAnimation();
    }
    public void setTarget(Vector2 target){
        this.target = new Vector2(target.x,target.y);
    }
    public void update(){
        speed = new Vector2(goTo(target));
        super.update();
        hitBox.setPosition(location);
    }

    private Vector2 goTo(Vector2 target){
        Vector2 speed = new Vector2(target.x-location.x, target.y-location.y);
        speed.nor();
        return speed;
    }

    public void draw(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, location.x, location.y);
    }

    private void сustomizeAnimation(){
        walkSheet = new Texture(Gdx.files.internal("AcuZavr3.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation<TextureRegion>(0.1f, walkFrames);
        stateTime = 0f;
    }
}
