package com.bowl.game.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bowl.game.Game;

class GameTest {
	
	private Game game;

    @BeforeEach                                         
    public void setUp() throws Exception {
    	game = new Game();
    }

	@Test
	@DisplayName("Test Rolls for Valid frame List")
	void testValidFrameList() {		
		List<Integer> rolls=Arrays.asList(10, 5, 5);
		assertEquals(true, game.checkAndBuildValidFrameList(rolls));		
	}
	
	@Test
	@DisplayName("Test Rolls for Invalid frame List")
	void testInValidFrameList() {		
		List<Integer> rolls=Arrays.asList(10, 8, 5);
		assertEquals(false, game.checkAndBuildValidFrameList(rolls));		
	}
	
	@Test
	@DisplayName("Test Perfect Strike")
	void testPerfectStrike() {		
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		assertEquals(300, game.score());		
	}
	
	@Test
	@DisplayName("Test Imperfect Game")
	void testImperfectStrike() {		
		game.roll(6);
		game.roll(2);
		game.roll(5);
		game.roll(3);
		game.roll(5);
		game.roll(2);
		game.roll(5);
		game.roll(3);
		game.roll(9);
		game.roll(0);
		game.roll(7);
		game.roll(3);
		game.roll(1);
		game.roll(6);
		game.roll(3);
		game.roll(3);
		game.roll(4);
		game.roll(2);
		game.roll(7);
		game.roll(0);
		assertEquals(77, game.score());		
	}
	
	@Test
	@DisplayName("Test Incomplete Game")
	void testIncompleteGame() {		
		game.roll(6);
		game.roll(2);
		game.roll(5);
		game.roll(3);
		game.roll(5);
		game.roll(2);
		game.roll(5);
		game.roll(3);
		game.roll(9);
		game.roll(0);
		game.roll(7);
		game.roll(3);
		game.roll(1);
		game.roll(6);
		game.roll(3);
		game.roll(3);
		assertEquals(64, game.score());		
	}

}
