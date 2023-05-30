/**
 * This package contains classes that represent the board game model.
 * The model maintains the state of the game, including the board, players, and game results.
 * It provides functionality for performing moves, checking for winners, and managing game results, etc...
 *
 * The main classes in this package are:
 * - BoardGameModel: Represents the board game model. It holds the board, players, and game state.
 * - GameResults: Represents the result record of a game, including player names, turns, and winner.
 * - GameResultsManager: Manages the game results, including saving and loading them from a JSON file.
 * - Player: Enumerates the possible players in the game (PLAYER1, PLAYER2).
 * - Square: Enumerates the possible states of a square on the board (NONE, RED, YELLOW, GREEN).
 *
 * Note:
 * This package assumes a JSON file named "game_results.json" is used to store the game results.
 */
package boardgame.model;
