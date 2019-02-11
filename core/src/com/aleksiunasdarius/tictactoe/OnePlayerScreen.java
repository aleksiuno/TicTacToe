/**
 * The "OnePlayerScreen" class implements main methods of
 * one player game
 *
 * @author  Darius Aleksiunas
 * @version 1.0
 * @since   28/04/2014
 */
package com.aleksiunasdarius.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class OnePlayerScreen implements Screen, InputProcessor {

	private TicTacToe game;

	private boolean drawMenu = false;
	private boolean playerTurn = true;
	private boolean winnerScreen = false;
	private boolean playerStarted = true;

	private int x = 1;
	private int o = 2;

	private int opLvl = 1;

	public OnePlayerScreen(TicTacToe game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		game.batch.setProjectionMatrix(game.camera.combined);

		game.batch.begin();

		game.batch.draw(game.backgroundTxt, 0, 0);
		game.batch.draw(game.gridTxt, 0, 0);

		for (int i = 0; i <= 8; i++) {
			if (game.gridMap[i] == 1)
				game.batch.draw(game.xTxt, game.xoCoordX[i], game.xoCoordY[i]);
			else if (game.gridMap[i] == 2)
				game.batch.draw(game.oTxt, game.xoCoordX[i], game.xoCoordY[i]);
		}
		if (drawMenu) {
			game.batch.draw(game.lowTxt, game.BTN_X, game.BTN_LOW_Y);
			game.batch.draw(game.medTxt, game.BTN_X, game.BTN_MED_Y);
			game.batch.draw(game.highTxt, game.BTN_X, game.BTN_HIGH_Y);
		}

		switch (opLvl) {
		case 1:
			game.batch.draw(game.lowTxt, game.BTN_X, game.BTN_LVL);
			break;
		case 2:
			game.batch.draw(game.medTxt, game.BTN_X, game.BTN_LVL);
			break;
		case 3:
			game.batch.draw(game.highTxt, game.BTN_X, game.BTN_LVL);
			break;
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

		if (Gdx.input.justTouched()) {
			int xTouch = Gdx.input.getX();
			int yTouch = Gdx.input.getY();

			Vector3 touchVect = new Vector3(xTouch, yTouch, 0);
			game.camera.unproject(touchVect);

			if (touchVect.x >= game.BTN_X
					&& touchVect.x <= game.SCREEN_WIDTH - game.BTN_X
					&& touchVect.y < game.lowTxt.getHeight()) {
				drawMenu = true;
			} else if (drawMenu) {
				if (touchVect.x > game.BTN_X
						&& touchVect.x < game.BTN_X + game.lowTxt.getWidth()) {
					if (touchVect.y >= game.BTN_LOW_Y
							&& touchVect.y <= game.BTN_LOW_Y
									+ game.lowTxt.getHeight()) {
						drawMenu = false;
						opLvl = 1;
					} else if (touchVect.y >= game.BTN_MED_Y
							&& touchVect.y <= game.BTN_MED_Y
									+ game.medTxt.getHeight()) {
						drawMenu = false;
						opLvl = 2;
					} else if (touchVect.y >= 8
							&& touchVect.y <= (8 + game.highTxt.getHeight())) {
						drawMenu = false;
						opLvl = 3;
					}
				}
			} else if (!drawMenu) {
				if (winnerScreen) {
					game.clearGrid();
					winnerScreen = false;

					if (playerStarted) {
						playerStarted = !playerStarted;
						playerTurn = false;
					} else {
						playerStarted = !playerStarted;
						playerTurn = true;
					}
				} else if (playerTurn) {
					int playerSymbol;

					if (playerStarted) {
						playerSymbol = x;
					} else {
						playerSymbol = o;
					}

					int crd = game.touchedGrid(touchVect.x, touchVect.y);
					// touchedHrid returns int > 0 <8; -1 == null
					if (crd != -1 && game.gridMap[crd] == 0) {
						game.gridMap[crd] = playerSymbol;

						if (game.checkIfWinned() > 0) {
							winnerScreen = true;
						} else if (game.checkIfWinned() == -2) {
							winnerScreen = true;
						} else {
							playerTurn = false;
						}
					}
				}
				if (!playerTurn) {
					int aiSymbol;
					int opSymbol;

					if (playerStarted) {
						aiSymbol = o;
						opSymbol = x;
					} else {
						aiSymbol = x;
						opSymbol = o;
					}

					AI ai = new AI(game.gridMap, aiSymbol, opSymbol);
					switch (opLvl) {
					case 1:
						game.gridMap = ai.easyPlayerMove();
						break;
					case 2:
						game.gridMap = ai.mediumPlayerMove();
						break;
					case 3:
						game.gridMap = ai.hardPlayerMove();
						break;
					}
					if (game.checkIfWinned() > 0) {
						winnerScreen = true;
					} else if (game.checkIfWinned() == -2) {
						winnerScreen = true;
					} else {
						playerTurn = true;
					}

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

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.BACK) {
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
