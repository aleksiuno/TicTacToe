package com.aleksiunasdarius.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

public class TwoPlayerScreen implements Screen, InputProcessor {
	
	private TicTacToe game;
	private boolean playerTurn = true;
	private boolean winnerScreen = false;

	public TwoPlayerScreen(TicTacToe game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		game.batch.setProjectionMatrix(game.camera.combined);
		game.batch.begin();
		game.batch.draw(game.backgroundTxt, 0, 0);
		game.batch.draw(game.gridTxt, 0, 0);

		for (int i = 0; i <= 8; i++)
		{
			if (game.gridMap[i] == 1) game.batch.draw(game.xTxt, game.xoCoordX[i], game.xoCoordY[i]);
			else if (game.gridMap[i] == 2) game.batch.draw(game.oTxt, game.xoCoordX[i], game.xoCoordY[i]);
		}
		if (winnerScreen == true) {
			if (game.checkIfWinned() == 1) {
				game.batch.draw(game.xWinner, 0, 0);
			} else if (game.checkIfWinned() == 2) {
				game.batch.draw(game.oWinner, 0, 0);
			} else {
				game.batch.draw(game.drawTxt, 0, 0);
			}
		}
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
			
			if (winnerScreen)
			{
				game.clearGrid();
				winnerScreen = false;
			}
			else if (playerTurn)
			{
				int crd = game.touchedGrid(touchVect.x, touchVect.y);
				//touchedHrid returns int > 0 <8; -1 == null
				if (crd != -1 && game.gridMap[crd] == 0)
				{
					game.gridMap[crd] = 1;

					if (game.checkIfWinned() > 0)
					{
						winnerScreen = true;
					}
					else if(game.checkIfWinned() == -2)
					{
						winnerScreen = true;
					}
					else
					{
						playerTurn = false;
					}
				}
			}
			else if (!playerTurn)
			{

					int crd = game.touchedGrid(touchVect.x, touchVect.y);
					if (crd != -1 && game.gridMap[crd] == 0)
					{
						game.gridMap[crd] = 2;

						if (game.checkIfWinned() > 0)
						{
							winnerScreen = true;
						}
						else if(game.checkIfWinned() == -2)
						{
							winnerScreen = true;
						}						
						playerTurn = true;					
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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.BACK){
			  game.setScreen(new MenuScreen(game));
			  game.clearGrid();
			  dispose(); 
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
