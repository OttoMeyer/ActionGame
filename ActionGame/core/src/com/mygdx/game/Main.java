package com.mygdx.game;

import com.mygdx.game.entity.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shape;
	Player player;
	Mouse mouse;
	ArrayList<Bullet> bullets;
	Terrane terrane;
	Music music;
	OrthographicCamera camera;
	Vector3 locationMouse;
	ArrayList<Еnemy> entitys;
	ArrayList<Totem> totems;
	SimpleEntity simpleEntity;
	//LiveEntity liveEntity;
	LiveEntity liveEntity;
	
	@Override
	public void create () {
		simpleEntity = new SimpleEntity(new Vector2(100, 100));
		liveEntity = new LiveEntity(new Vector2(200, 200), new Vector2(0, 0), 100);
		player = new Player(100,100,100);
		mouse = new Mouse();
		bullets = new ArrayList<>();
		shape = new ShapeRenderer();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		terrane = new Terrane();
		music = Gdx.audio.newMusic(Gdx.files.internal("BimBamBum.wav"));
		music.setLooping(true);
		//music.play();
		camera = new OrthographicCamera(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
		player.weapon.chengeWeapon("Sword");
		entitys = new ArrayList<>();
		entitys.add(new Еnemy(new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)));
		totems = new ArrayList<>();
		totems.add(new Totem(new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2), 100));
	}

	@Override
	public void render () {
		locationMouse = camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
		camera.position.set(player.location.x, player.location.y, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		terrane.draw(batch);
		batch.end();
		shape.begin(ShapeRenderer.ShapeType.Line);
		player.update(terrane.hitBoxes);
		mouse.draw(shape);

		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
		{
			bullets.add(new Bullet(player.location.x, player.location.y,
					locationMouse.x, locationMouse.y));
		}

		shape.end();
		batch.begin();
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
			for(Еnemy entity: entitys){
				player.makeHit(entity, locationMouse, batch);
			}

		for(Bullet bullet: bullets){
			bullet.update();
			bullet.draw(batch);
		}
		player.weapon.update(player.location, locationMouse);
		player.weapon.draw(batch);
		player.draw(batch);
		//entity.setTarget(new Vector2(player.location));

		for (int i = 0; i < entitys.size(); i++) { // Уничтожение блоков
			Еnemy e = entitys.get(i);
			if (e.HP <= 0) {
				entitys.remove(e);
				i--;
			}
			else{
				//player.test(e);
				e.setTarget(new Vector2(player.location));
				e.update();
				e.draw(batch);
			}
		}

		for(Totem t: totems){
			t.update(entitys);
			t.draw(batch);
		}

		simpleEntity.draw(batch);
		liveEntity.update();
		liveEntity.draw(batch);

		batch.end();

		for (int i = 0; i < bullets.size(); i++) { // Уничтожение блоков
			Bullet b = bullets.get(i);
			if (b.destroy) {
				bullets.remove(b);
				i--;
			}
		}


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
