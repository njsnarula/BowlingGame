package com.bowl.game;

public interface GameInterface {
	
	int MAX_ROLLS = 21;
	int MAX_FRAMES = 10;
	
	/**
	 * This method is used to collect the No. of Pins Knocked during a roll in the game.
	 * It can be called multiple times and each call will record a roll and no of Pins knocked in that roll for a game.
	 * @param noOfPinsKnocked
	 */
	public void roll(int noOfPinsKnocked);
	
	
	/**
	 * This method is called to calculate the total score for a game based on the rolls recorded with the roll method so far.
	 * It returns -1 if the count of rolls or order of number of pins knocked is not correct,
	 * A Zero or Non Zero score if the output is valid
	 * @return
	 */
	public int score();
}
