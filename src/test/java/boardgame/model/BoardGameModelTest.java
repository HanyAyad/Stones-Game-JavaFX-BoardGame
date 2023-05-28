package boardgame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameModelTest {

    private BoardGameModel model;

    @BeforeEach
    void setUp() {
        model = new BoardGameModel();
        model.setPlayerNames("Player 1","Player 2");
    }

    @Test
    void squareProperty() {
        assertNotNull(model.squareProperty(0, 0));
    }

    @Test
    void getSquare() {
        assertEquals(Square.NONE, model.getSquare(0, 0));
    }

    @Test
    void testToString() {
        String expected = "0 0 0 \n0 0 0 \n0 0 0 \n";
        assertEquals(expected, model.toString());
    }

    @Test
    void checkRow_noWinner() {
        assertFalse(model.checkRow(0));
    }

    @Test
    void checkRow_winnerExists() {
        model.move(0, 0);
        model.move(0, 1);
        model.move(0, 2);
        assertTrue(model.checkRow(0));
    }

    @Test
    void checkColumn_noWinner() {
        assertFalse(model.checkColumn(0));
    }

    @Test
    void checkColumn_winnerExists() {
        model.move(0, 0);
        model.move(1, 0);
        model.move(2, 0);
        assertTrue(model.checkColumn(0));
    }

    @Test
    void checkDiagonal_noWinner() {
        assertFalse(model.checkDiagonal());
    }

    @Test
    void checkDiagonal_winnerExists() {
        model.move(0, 0);
        model.move(1, 1);
        model.move(2, 2);
        assertTrue(model.checkDiagonal());
    }

    @Test
    void getCurrentPlayer() {
        assertEquals(Player.PLAYER1, model.getCurrentPlayer());
        model.move(0, 0);
        assertEquals(Player.PLAYER2, model.getCurrentPlayer());
    }

    @Test
    void isGameOver_gameNotOver() {
        assertFalse(model.isGameOver());
    }

    @Test
    void isGameOver_gameOver() {
        model.move(0, 0);
        model.move(0, 1);
        model.move(0, 2);
        assertTrue(model.isGameOver());
    }

    @Test
    void getWinner_noWinner() {
        assertNull(model.getWinner());
    }

    @Test
    void getWinner_winnerExists() {
        model.move(0, 0);
        model.move(0, 1);
        model.move(0, 2);
        assertEquals(Player.PLAYER1, model.getWinner());
    }

    @Test
    void resetGame() {
        model.move(0, 0);
        model.move(0, 1);
        model.move(0, 2);
        model.resetGame();
        assertEquals(Player.PLAYER1, model.getCurrentPlayer());
        assertFalse(model.isGameOver());
        assertNull(model.getWinner());
        assertNull( model.gameResult.getWinnerName());
        assertEquals(0, model.gameResult.getPlayer1Turns());
        assertEquals(0, model.gameResult.getPlayer2Turns());
    }

    @Test
    void setPlayerNames() {
        assertEquals("Player 1", model.player1Name);
        assertEquals("Player 2", model.player2Name);
        assertEquals("Player 1", model.gameResult.getPlayer1Name());
        assertEquals("Player 2", model.gameResult.getPlayer2Name());
    }

    @Test
    void setWinnerName() {
        model.setWinnerName("Player 1");
        assertEquals("Player 1", model.winnerName);
        assertEquals("Player 1", model.gameResult.getWinnerName());
    }
}
