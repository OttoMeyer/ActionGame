package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Skill {
    String name;
    public Skill(String name){
        this.name = name;
    }
    public void useSkill(Еnemy entity, Vector3 mouse, SpriteBatch batch){
        switch(name){
            case "Slash":
                //Slash(entity, mouse, batch);
                break;
        }
    }

    public void Slash(Еnemy entity, Vector3 mouse, SpriteBatch batch, Sprite spriteHit){
        /*
        weapon.spriteHit.setColor(Color.WHITE);
        weapon.spriteHit.setPosition(weapon.location.x, weapon.location.y);
        weapon.spriteHit.setRotation(weapon.angle);

        weapon.spriteHit.draw(batch);
        double R = Math.pow((location.x - entity.location.x),2) + Math.pow((location.y - entity.location.y),2);
        float eng = new Vector2(mouse.x - location.x, mouse.y - location.y).angleDeg();
        float engEn = new Vector2(entity.location.x - location.x, entity.location.y - location.y).angleDeg();
        if (R < Math.pow(64,2) && ((engEn > eng - 45 && engEn < eng + 45)
                || (engEn - 360 > eng - 45 && engEn < eng + 45) || (engEn > eng - 45 && engEn + 360 < eng + 45))) {
            entity.sprite.setColor(Color.RED);
            entity.HP -= 25;
            System.out.println(eng);
        }

         */
    }

}
