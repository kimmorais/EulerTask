package org.task;

import java.io.*;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Problem {

    private static final String PLAYER_ONE_WINS = "Player 1 wins %s hands!";
    private final Logger logger = getLogger(Problem.class.getName());

    public void solve() throws IOException {

        var file = new File("C:\\poker.txt");

        try (var inputStream = new FileInputStream(file)) {

            var inputStreamReader = new InputStreamReader(inputStream);
            var bufferedReader = new BufferedReader(inputStreamReader);
            var parser = new Parser(bufferedReader);

            var count = 0;

            while (parser.hasNext()) {

                count = playerOneWinCount(parser, count);
            }

            var message = String.format(PLAYER_ONE_WINS, count);
            logger.info(message);
        }
    }

    private int playerOneWinCount(Parser parser, int count) throws IOException {

        var round = parser.getRound();

        if (Decider.playerOneWins(round)) {

            count++;
        }

        return count;
    }
}
