package org.task;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        try (var inputStream = Main.class.getResourceAsStream("poker.txt")) {
        }
    }
}