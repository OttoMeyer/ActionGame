package com.mygdx.game.interfacesPackage;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.LiveEntity;

import java.util.ArrayList;

public interface Skill {
    public void use(LiveEntity liveEntity, Vector2 location);
}
