package org.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

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

            System.out.println("Player 1 wins " + count + " hands!");
        }
    }
}