package org.task;

import org.jetbrains.annotations.NotNull;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Round;

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

    public Round getRound() throws IOException {

        var cards = readCards();

        var playerOneHand = getPlayerHand(cards, 0, 5);
        var playerTwoHand = getPlayerHand(cards, 5, 10);

        return new Round(playerOneHand, playerTwoHand);
    }

    private String[] readCards() throws IOException {

        var currentLine = bufferedReader.readLine();
        return currentLine.split(SPLIT);
    }

    @NotNull
    private Hand getPlayerHand(String[] cards, int begin, int end) {

        return new Hand(getCards(cards, begin, end));
    }

    private List<Card> getCards(String[] cards, int begin, int end) {

        var listOfCards = new ArrayList<Card>();

        for (int i = begin; i < end; i++) {

            var card = new Card(cards[i]);
            listOfCards.add(card);
        }

        return listOfCards;
    }

    public Boolean hasNext() throws IOException {

        return bufferedReader.ready();
    }
}