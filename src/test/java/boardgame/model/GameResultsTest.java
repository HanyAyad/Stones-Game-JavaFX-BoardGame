package boardgame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameResultsTest {

    private GameResults results;

    @BeforeEach
    void setUp() {
        results = new GameResults();
    }

    @Test
    void getGameStartTime() {
        results.setGameStartTime("2023-05-24 10:00:00");
        assertEquals("2023-05-24 10:00:00", results.getGameStartTime());
    }

    @Test
    void getPlayer1Name() {
        results.setPlayer1Name("Player 1");
        assertEquals("Player 1", results.getPlayer1Name());
    }

    @Test
    void getPlayer2Name() {
        results.setPlayer2Name("Player 2");
        assertEquals("Player 2", results.getPlayer2Name());
    }

    @Test
    void getPlayer1Turns() {
        results.setPlayer1Turns(5);
        assertEquals(5, results.getPlayer1Turns());
    }

    @Test
    void getPlayer2Turns() {
        results.setPlayer2Turns(3);
        assertEquals(3, results.getPlayer2Turns());
    }


    @Test
    void getWinnerName() {
        results.setWinnerName("Player 1");
        assertEquals("Player 1", results.getWinnerName());
    }

}
