/**
* The "PicTacToe" class implements main methods of
* the game.
*
* @author  Darius Aleksiunas
* @version 1.0
* @since   28/04/2014
*/

package com.aleksiunasdarius.tictactoe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TicTacToe extends Game {
	
	public static OrthographicCamera camera;
	
	public static final int SCREEN_HEIGHT = 24;
	public static final int SCREEN_WIDTH = 40;
	
	public static final int BTN_X = 2;
	public static final int BTN_LOW_Y = 24;
	public static final int BTN_MED_Y = 16;
	public static final int BTN_HIGH_Y = 8;
	public static final int BTN_LVL = 0;
	
	public SpriteBatch batch;
	
	public Texture backgroundTxt;
	public Texture gridTxt;
	public Texture poTxt;
	public Texture ptTxt;
	public Texture lowTxt;
	public Texture medTxt;
	public Texture highTxt;
	public Texture xTxt;
	public Texture oTxt;
	public Texture xWinner;
	public Texture oWinner;
	public Texture drawTxt;
	
	public Sprite xSprt;
	public Sprite oSprt;
	
	public static int xoCoordX[] = {
			2, 9, 16, 
			2, 9, 16, 
			2, 9, 16};

	public static int xoCoordY[] = {
			24, 24, 24, 
			17, 17, 17, 
			10, 10, 10};

	public static int gridMap[] = {
			0, 0, 0, 
			0, 0, 0, 
			0, 0, 0};

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_HEIGHT, SCREEN_WIDTH);
		batch = new SpriteBatch();
		
		Gdx.input.setCatchBackKey(true);
		
		backgroundTxt = new Texture(Gdx.files.internal("background.png"));
		gridTxt = new Texture(Gdx.files.internal("grid.png"));
		poTxt = new Texture(Gdx.files.internal("opbtn.png"));
		ptTxt = new Texture(Gdx.files.internal("tpbtn.png"));
		lowTxt = new Texture(Gdx.files.internal("lowbtn.png"));
		medTxt = new Texture(Gdx.files.internal("medbtn.png"));
		highTxt = new Texture(Gdx.files.internal("highbtn.png"));
		xTxt = new Texture(Gdx.files.internal("x.png"));
		oTxt = new Texture(Gdx.files.internal("o.png"));
		xWinner = new Texture(Gdx.files.internal("xwinner.png"));	
		oWinner = new Texture(Gdx.files.internal("owinner.png"));
		drawTxt = new Texture(Gdx.files.internal("draw.png"));
		
		setScreen(new MenuScreen(this));
		
	}

	@Override
	public void dispose() {
		backgroundTxt.dispose();
		gridTxt.dispose();
		poTxt.dispose();
		ptTxt.dispose();
		lowTxt.dispose();
		medTxt.dispose();
		highTxt.dispose();
		xTxt.dispose();
		oTxt.dispose();
		oWinner.dispose();
		xWinner.dispose();
		batch.dispose();
		super.dispose();
	}
	
	public void clearGrid()
	{
		for (int i = 0; i <= 8; i++)
		{
			gridMap[i] = 0;
		}
	}

	public int touchedGrid(float x, float y)
	{
		//returns from 0 to 8 depends which grid
		//possition is pressed
		int pos = -1;
		if (y > 10 && y < 17)
		{
			if (x > 2 && x < 9) pos = 6;
			if (x > 9 && x < 16)pos = 7;
			if (x > 16 && x < 23)pos = 8;
		}
		if (y > 17 && y < 24)
		{
			if (x > 2 && x < 9) pos = 3;
			if (x > 9 && x < 16)pos = 4;
			if (x > 16 && x < 23)pos = 5;
		}
		if (y > 24 && y < 31)
		{
			if (x > 2 && x < 9) pos = 0;
			if (x > 9 && x < 16)pos = 1;
			if (x > 16 && x < 23)pos = 2;
		}
		return pos;
	}
	
	public int checkIfWinned()
	{
		//returns -1 if no one wins, -2 if grid is full
		//and no one wins.
		int chck = -1;

		if (gridMap[0] == gridMap[1] && gridMap[1] == gridMap[2] && gridMap[0] > 0){chck = gridMap[0];}
		else if (gridMap[3] == gridMap[4] && gridMap[4] == gridMap[5] && gridMap[3] > 0){chck = gridMap[3];}
		else if (gridMap[6] == gridMap[7] && gridMap[7] == gridMap[8] && gridMap[6] > 0){chck = gridMap[6];}

		else if (gridMap[0] == gridMap[3] && gridMap[3] == gridMap[6] && gridMap[0] > 0){chck = gridMap[0];}
		else if (gridMap[1] == gridMap[4] && gridMap[4] == gridMap[7] &&gridMap[1] > 0){chck = gridMap[1];}
		else if (gridMap[2] == gridMap[5] && gridMap[5] == gridMap[8] && gridMap[2] > 0){chck = gridMap[2];}

		else if (gridMap[0] == gridMap[4] && gridMap[4] == gridMap[8] && gridMap[0] > 0){chck = gridMap[0];}
		else if (gridMap[2] == gridMap[4] && gridMap[4] == gridMap[6] && gridMap[2] > 0){chck = gridMap[2];}
		else
		{
			boolean isZeros = false;
			for(int i = 0; i<= 8; i++ ){
				if(gridMap[i] == 0){
					isZeros = true;
				}
			}
			if(isZeros == false){chck = -2;}
		}						
		return chck;
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void setScreen(Screen screen) {
		// TODO Auto-generated method stub
		super.setScreen(screen);
	}

	@Override
	public Screen getScreen() {
		// TODO Auto-generated method stub
		return super.getScreen();
	}

}
