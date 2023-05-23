// GameResultsManager.java
package boardgame.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResultsManager {
    private List<GameResults> gameResultsList;
    private static final String FILE_NAME = "game_results.json";

    public GameResultsManager() {
        gameResultsList = loadGameResults();
    }

    public void saveGameResult(GameResults gameResult) {
        gameResultsList.add(gameResult);
        writeGameResults();
    }

    public Map<String, Long> getPlayerWins() {
        return gameResultsList.stream()
                .collect(Collectors.groupingBy(GameResults::getWinnerName, Collectors.counting()));
    }

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
