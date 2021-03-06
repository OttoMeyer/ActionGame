package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.HitBox;

public class LiveEntity extends SimpleEntity {

    private int FRAME_COLS, FRAME_ROWS;
    public int HP;
    public Vector2 speed;
    TextureRegion[] animationFrames;
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    float stateTime;

    public LiveEntity(Vector2 location, Vector2 speed, int HP) {
        super(location);
        FRAME_COLS = 1;
        FRAME_ROWS = 1;
        this.speed = new Vector2(speed);
        this.HP = HP;
        сustomizeAnimation("Nothing.png");
    }

    public LiveEntity(Vector2 location, Vector2 speed, int HP, String nameSprite) {
        super(location, nameSprite);
        FRAME_COLS = 1;
        FRAME_ROWS = 1;
        this.speed = new Vector2(speed);
        this.HP = HP;
        сustomizeAnimation(nameSprite);
    }

    public void update(){
        location.x += speed.x * 100 * Gdx.graphics.getDeltaTime();
        location.y += speed.y * 100 * Gdx.graphics.getDeltaTime();
        hitBox.setPosition(location);
    }

    public void draw(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, location.x, location.y);
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer){
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, location.x, location.y);
        hitBox.showHimself(shapeRenderer);
    }


    private void сustomizeAnimation(String nameSprite){
        walkSheet = new Texture(Gdx.files.internal(nameSprite));
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
