package boardgame.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameResults {

    @NonNull private String gameStartTime;
    @NonNull private String player1Name;
    @NonNull private String player2Name;
    @NonNull private int player1Turns=0;
    @NonNull private int player2Turns=0;
    private String winnerName;

  }