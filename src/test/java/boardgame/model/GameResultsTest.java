package boardgame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameResultsTest {

    private GameResults results;

    @BeforeEach
    void setUp() {
        results = new GameResults("2023-05-24 10:00:00","Player 1","Player 2",2,1,"Player 1");
    }

    @Test
    void getGameStartTime() {
        assertEquals("2023-05-24 10:00:00", results.getGameStartTime());
    }

    @Test
    void getPlayer1Name() {
        assertEquals("Player 1", results.getPlayer1Name());
    }

    @Test
    void getPlayer2Name() {
        assertEquals("Player 2", results.getPlayer2Name());
    }

    @Test
    void getPlayer1Turns() {
        assertEquals(2, results.getPlayer1Turns());
    }

    @Test
    void getPlayer2Turns() {
        assertEquals(1, results.getPlayer2Turns());
    }


    @Test
    void getWinnerName() {
        assertEquals("Player 1", results.getWinnerName());
    }

    @Test
    void testToString() {
        String expected = "GameResults(" +
                "gameStartTime=2023-05-24 10:00:00, " +
                "player1Name=Player 1, " +
                "player2Name=Player 2, " +
                "player1Turns=2, " +
                "player2Turns=1, " +
                "winnerName=Player 1)";

        assertEquals(expected, results.toString());
    }


}
