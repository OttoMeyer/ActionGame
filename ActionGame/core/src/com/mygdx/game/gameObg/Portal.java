package com.mygdx.game.gameObg;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.SimpleEntity;

public class Portal extends SimpleEntity {
    public Portal(Vector2 location){
        super(location, "Portal.png");
    }
}
