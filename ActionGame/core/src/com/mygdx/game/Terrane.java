package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class Terrane {
    String[][][] map;
    HashMap <String, Sprite[]> decoder;
    ArrayList<HitBox> hitBoxes;
    public Terrane(){
        hitBoxes = new ArrayList<>();
        decoder = new HashMap<>();
        loadTexture("Grass", "Grass2.png");
        loadTexture("Block", "Blocks.png");

        map = new String[100][100][2];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j <map[0].length; j++){
                map[i][j][0] = "Grass";
                map[i][j][1] = String.valueOf(MathUtils.random(0,3));
            }
        }
        for(int i = 0; i < map.length; i++){
            map[i][0][0] = "Block";
            hitBoxes.add(new HitBox(16, 16, i*16, 0));
            map[i][map[0].length-1][0] = "Block";
            hitBoxes.add(new HitBox(16, 16, i*16, (map[0].length-1) * 16));
        }
        for(int i = 0; i < map[0].length; i++){
            map[0][i][0] = "Block";
            hitBoxes.add(new HitBox(16, 16, 0,i*16));
            map[map.length-1][i][0] = "Block";
            hitBoxes.add(new HitBox(16, 16, (map.length-1) * 16, i*16));
        }
    }
    public void draw(SpriteBatch batch){
        for (int x = 0; x < map.length; x++)
            for (int y = 0; y < map.length; y++){
                Sprite tmp = new Sprite(decoder.get(map[x][y][0])[Integer.parseInt(map[x][y][1])]);
                tmp.setPosition(x*16,y*16);
                tmp.draw(batch);
            }
    }
    private void loadTexture(String name, String file){
        TextureRegion[][] tmp = TextureRegion.split(new Texture(file),16,16);
        Sprite[] sprites = new Sprite[tmp.length * tmp[0].length];
        int index = 0;
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[0].length; j++) {
                sprites[index++] = new Sprite(new TextureRegion(tmp[i][j]));
            }
        }
        decoder.put(name, sprites);
    }
}
