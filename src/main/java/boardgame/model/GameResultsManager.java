package boardgame.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



/**
 The GameResultsManager class is responsible for managing the game results.
 The class manages saving and loading game results to/from a JSON file.
 The class also contains a method to get the win count for players.
 */
public class GameResultsManager {
    private List<GameResults> gameResultsList;
    private static final String FILE_NAME = "game_results.json";



    /**
     * Constructs a GameResultsManager object.
     * It initializes the gameResultsList by loading game results from the JSON file.
     */
    public GameResultsManager() {
        gameResultsList = loadGameResults();
    }



    /**
     * Method saves a game result by adding it to the gameResultsList and writing the updated list to the JSON file.
     *
     * @param gameResult the game result to be saved
     */
    public void saveGameResult(GameResults gameResult) {
        gameResultsList.add(gameResult);
        writeGameResults();
    }



    /**
     * Method retrieves the player wins from the game results.
     * It returns a map where the keys are the winner names and the values are the corresponding counts of wins.
     *
     * @return a map of winner names and their win counts
     */
    public Map<String, Long> getPlayerWins() {
        return gameResultsList.stream()
                .collect(Collectors.groupingBy(GameResults::getWinnerName, Collectors.counting()));
    }



    /**
     * Method loads game results from the JSON file.
     * It reads the contents of the file and maps them to a list of GameResults objects.
     *
     * @return the list of loaded game results
     */
    private List<GameResults> loadGameResults() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(FILE_NAME);

        if (file.exists()) {
            try {
                return mapper.readValue(file, new TypeReference<List<GameResults>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<>();
    }



    /**
     * Method writes the game results to JSON file.
     * It converts the gameResultsList to JSON format and writes it to the file.
     */
    private void writeGameResults() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(FILE_NAME);

        try {
            mapper.writeValue(file, gameResultsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
