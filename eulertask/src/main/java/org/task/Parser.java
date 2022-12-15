package org.task;

import org.task.model.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final String SPLIT = " ";

    private final BufferedReader bufferedReader;

    public Parser(InputStream is) {

        this.bufferedReader = new BufferedReader(new InputStreamReader(is));
    }

    public List<List<Card>> getPlayerHands() throws IOException {

        var currentLine = bufferedReader.readLine();
        var cards = currentLine.split(SPLIT);

        var playerOneHand = getCards(cards, 0, 5);
        var playerTwoHand = getCards(cards, 5, 10);

        return List.of(playerOneHand, playerTwoHand);
    }

    private List<Card> getCards(String[] cards, int begin, int end) {

        var listOfCards = new ArrayList<Card>();

        for (int i = begin; i < end; i++) {

            var card = new Card(cards[i]);
            listOfCards.add(card);
        }

        return listOfCards;
    }
}