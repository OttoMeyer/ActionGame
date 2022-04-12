package com.mygdx.game.skillsPackage;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.LiveEntity;
import com.mygdx.game.interfacesPackage.Skill;

import java.util.ArrayList;

public class Slash implements Skill {

    public void use(LiveEntity liveEntity, Vector2 location){
        float r = liveEntity.location.dst2(location);
        if(r<100){
            liveEntity.HP -= 40;
        }

    }
}
