package org.task;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class Main {

    private static final String PLAYER_ONE_WINS = "Player 1 wins %s hands!";

    public static void main(String[] args) throws IOException {

        var file = new File("C:\\poker.txt");

        try (var inputStream = new FileInputStream(file)) {

            var parser = new Parser(inputStream);

            var count = 0;

            while (parser.hasNext()) {

                var round = parser.getRound();

                if (round.playerOneWins()) {

                    count++;
                }
            }

           log.info(String.format(PLAYER_ONE_WINS, count));
        }
    }
}