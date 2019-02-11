/**
 * The "AI" class implements artificial intelligence
 * methods.
 *
 * @author  Darius Aleksiunas
 * @version 1.0
 * @since   28/04/2014
 */

package com.aleksiunasdarius.tictactoe;

public class AI {
	private int gridMap[];
	private int aiSymbol;
	private int opSymbol;

	public final int[][] WINNING_SEQUENCES = { { 0, 1, 2 }, { 3, 4, 5 },
			{ 6, 7, 8 },

			{ 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },

			{ 0, 4, 8 }, { 2, 4, 6 } };

	public AI(int gridMap[], int aiSymbol, int opSymbol) {
		this.gridMap = gridMap;
		this.aiSymbol = aiSymbol;
		this.opSymbol = opSymbol;

	}

	public int[] easyPlayerMove() {
			randomMove();
		return gridMap;
	}

	public int[] mediumPlayerMove() {
		if (finishTwoInRow(aiSymbol) != -1) {
			gridMap[finishTwoInRow(aiSymbol)] = aiSymbol;
		} else 
		{
			randomMove();
		}
		return gridMap;
	}

	public int[] hardPlayerMove() {
		if (finishTwoInRow(aiSymbol) != -1) {
			gridMap[finishTwoInRow(aiSymbol)] = aiSymbol;
		} else if (finishTwoInRow(opSymbol) != -1) {
			gridMap[finishTwoInRow(opSymbol)] = aiSymbol;
		} else if (gridMap[4] == 0) {
			gridMap[4] = aiSymbol;
		} else if (isOponOposCornEmt() != -1) {
			gridMap[isOponOposCornEmt()] = aiSymbol;
		} else if (isCornerUsed() != -1) {
			gridMap[isCornerUsed()] = aiSymbol;
		} else {
			randomMove();
		}
		return gridMap;
	}

	public int finishTwoInRow(int symbol) {
		/**
		 * This method checks if there are two same symbols in winning sequence
		 */
		int data = -1;

		for (int i = 0; i <= 7; i++) {
			if (gridMap[WINNING_SEQUENCES[i][0]] == symbol
					&& gridMap[WINNING_SEQUENCES[i][1]] == symbol
					&& gridMap[WINNING_SEQUENCES[i][2]] == 0) {
				data = WINNING_SEQUENCES[i][2];
			} else if (gridMap[WINNING_SEQUENCES[i][1]] == symbol
					&& gridMap[WINNING_SEQUENCES[i][2]] == symbol
					&& gridMap[WINNING_SEQUENCES[i][0]] == 0) {
				data = WINNING_SEQUENCES[i][0];
			} else if (gridMap[WINNING_SEQUENCES[i][0]] == symbol
					&& gridMap[WINNING_SEQUENCES[i][2]] == symbol
					&& gridMap[WINNING_SEQUENCES[i][1]] == 0) {
				data = WINNING_SEQUENCES[i][1];
			}
		}
		return data;
	}

	public int isCornerUsed() {
		int data = -1;
		if (gridMap[0] == 0) {
			data = 0;
		} else if (gridMap[2] == 0) {
			data = 2;
		} else if (gridMap[6] == 0) {
			data = 6;
		} else if (gridMap[8] == 0) {
			data = 8;
		}
		return data;
	}

	public int isOponOposCornEmt() {
		int data = -1;
		if (gridMap[0] == opSymbol && gridMap[oppositeCorner(0)] == 0) {
			data = oppositeCorner(0);
		} else if (gridMap[2] == opSymbol && gridMap[oppositeCorner(2)] == 0) {
			data = oppositeCorner(2);
		} else if (gridMap[6] == opSymbol && gridMap[oppositeCorner(6)] == 0) {
			data = oppositeCorner(6);
		} else if (gridMap[8] == opSymbol && gridMap[oppositeCorner(8)] == 0) {
			data = oppositeCorner(8);
		}
		return data;
	}

	public int oppositeCorner(int cornerCoord) {
		int data = -1;
		if (cornerCoord == 0)
			data = 8;
		else if (cornerCoord == 2)
			data = 6;
		else if (cornerCoord == 6)
			data = 2;
		else if (cornerCoord == 8)
			data = 0;
		return data;
	}
	
	public void randomMove(){
		while (true) {
			int i = (int) (Math.random() * gridMap.length);
			if (gridMap[i] == 0) {
				gridMap[i] = aiSymbol;
				break;
			}
		}
	}

}
