package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
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
import com.mygdx.game.gameObg.Enemy;
import com.mygdx.game.gameObg.Player;
import com.mygdx.game.gameObg.Portal;
import com.mygdx.game.gameObg.Totem;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
	SpriteBatch sceneBatch, menuBatch;
	ShapeRenderer sceneShape, menuShape;
	Player player;
	Mouse mouse;
	Terrane terrane;
	Music music;
	OrthographicCamera camera;
	Vector3 locationMouse;
	ArrayList<Enemy> entitys;
	ArrayList<Totem> totems;
	Portal portal;
	
	@Override
	public void create () {
		portal = new Portal(new Vector2(500, 500));
		player = new Player(100,100,100);
		mouse = new Mouse();
		sceneShape = new ShapeRenderer();
		menuShape = new ShapeRenderer();
		sceneBatch = new SpriteBatch();
		terrane = new Terrane();
		music = Gdx.audio.newMusic(Gdx.files.internal("BimBamBum.wav"));
		music.setLooping(true);
		//music.play();
		camera = new OrthographicCamera(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
		player.weapon.chengeWeapon("Sword");
		entitys = new ArrayList<>();
		entitys.add(new Enemy(new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)));
		totems = new ArrayList<>();
		totems.add(new Totem(new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2), 100));
	}

	@Override
	public void render () {
		locationMouse = camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
		camera.position.set(player.location.x, player.location.y, 0);
		camera.update();
		sceneBatch.setProjectionMatrix(camera.combined);
		sceneShape.setProjectionMatrix(camera.combined);
		ScreenUtils.clear(0, 0, 0, 1);


		player.update(terrane.hitBoxes);



		graficAll();

		sceneShape.begin(ShapeRenderer.ShapeType.Line);
		sceneShape.setColor(Color.RED);
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			ArrayList<LiveEntity> liveEntities = new ArrayList<>();
			liveEntities.clear();
			liveEntities.addAll(entitys);
			liveEntities.addAll(totems);
			for (LiveEntity entity : liveEntities) {
				player.makeHit(entity, locationMouse, sceneShape);
			}
		}
		sceneShape.end();

	}
	
	@Override
	public void dispose () {
		sceneBatch.dispose();
	}
	private void graficAll(){ // Сначало всё удаляем/ дальше всё обновляем/ Дальше всё рисуем
		removeAll();
		updateAll();
		drawAll();
		showAllHitBox();
	}

	private void removeAll() {

		ArrayList<LiveEntity> liveEntities = new ArrayList<>();
		liveEntities.addAll(entitys);
		liveEntities.addAll(totems);

		for (int i = 0; i < liveEntities.size(); i++) { // Уничтожение всех объектов
			LiveEntity e = liveEntities.get(i);
			if (e.HP <= 0) {
				if (e.getClass() == Enemy.class) {
					entitys.remove(e);
				} else if (e.getClass() == Totem.class) {
					totems.remove(e);
				}
				liveEntities.remove(e);
				i--;
			}
		}
	}

	private void updateAll(){
		player.weapon.update(player.location, locationMouse); // Почему мечь рисуется сам по себе а не в player?
		 for(Enemy enemy: entitys){
			 enemy.setTarget(player.location);
			 enemy.update();
		 }

		 for(Totem totem: totems){
			 totem.update(entitys);
		 }

	}

	private void drawAll(){
		sceneBatch.begin(); // Рисуем карту
		terrane.draw(sceneBatch);
		sceneBatch.end();

		menuShape.begin(ShapeRenderer.ShapeType.Line); // Рисуем указатель
		mouse.draw(menuShape);
		menuShape.end();

		sceneBatch.begin();
		player.weapon.draw(sceneBatch);
		player.draw(sceneBatch);

		for(Enemy enemy: entitys){
			enemy.draw(sceneBatch);
		}

		for(Totem t: totems){ // Обдейтим все тотемы
			t.draw(sceneBatch);
		}

		portal.draw(sceneBatch);

		sceneBatch.end();
	}

	private void showAllHitBox(){
		//menuShape sceneShape
		sceneShape.begin(ShapeRenderer.ShapeType.Line);
		player.drawHitBox(sceneShape);

		for(Enemy enemy: entitys){
			enemy.drawHitBox(sceneShape);
		}

		for(Totem t: totems){ // Обдейтим все тотемы
			t.drawHitBox(sceneShape);
		}

		portal.drawHitBox(sceneShape);

		sceneShape.end();

		menuShape.begin(ShapeRenderer.ShapeType.Line);
		menuShape.line(Gdx.graphics.getWidth()/2,0, Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight());
		menuShape.line(0,Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/2);
		menuShape.end();

	}

}
