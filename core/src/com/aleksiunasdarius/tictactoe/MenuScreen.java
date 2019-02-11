package com.aleksiunasdarius.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen implements Screen, InputProcessor {
	
	private TicTacToe game;
	
	public MenuScreen(TicTacToe game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		
		game.batch.setProjectionMatrix(game.camera.combined);
		game.batch.begin();
		game.batch.draw(game.backgroundTxt, 0, 0);
		game.batch.draw(game.poTxt, game.BTN_X, game.BTN_LOW_Y);
		game.batch.draw(game.ptTxt, game.BTN_X, game.BTN_MED_Y);
		game.batch.end();
		
		handleInput();

	}

	private void handleInput() {
		
		if (Gdx.input.justTouched())
		{
			int xTouch = Gdx.input.getX();
			int yTouch = Gdx.input.getY();

			Vector3 touchVect = new Vector3(xTouch, yTouch, 0);
			game.camera.unproject(touchVect);
			
			if (touchVect.x > game.BTN_X && touchVect.x < game.BTN_X + game.poTxt.getWidth())
			{
				if (touchVect.y >= game.BTN_LOW_Y && touchVect.y <= game.BTN_LOW_Y + game.poTxt.getHeight())
				{
					game.setScreen(new OnePlayerScreen(game));
					dispose();
				}
				if (touchVect.y >= game.BTN_MED_Y && touchVect.y <= game.BTN_MED_Y + game.poTxt.getHeight())
				{
					game.setScreen(new TwoPlayerScreen(game));
					dispose();
				}					
			}
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.BACK){
			Gdx.app.exit();
	        }
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
