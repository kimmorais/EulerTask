package org.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class Main {

    private static final String PLAYER_ONE_WINS = "Player 1 wins %s hands!";
    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {

        var file = new File("C:\\poker.txt");

        try (var inputStream = new FileInputStream(file)) {

            var parser = new Parser(inputStream);
            var count = 0;

            while (parser.hasNext()) {

                var round = parser.getRound();

                if (Decider.playerOneWins(round)) {

                    count++;
                }
            }

            var message = String.format(PLAYER_ONE_WINS, count);
            logger.info(message);
        }
    }
}