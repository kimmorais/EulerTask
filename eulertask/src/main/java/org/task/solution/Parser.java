package org.task.solution;

import org.jetbrains.annotations.NotNull;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Round;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {

    private static final String SPLIT = " ";

    private final BufferedReader bufferedReader;

    public Parser(BufferedReader br) {

        this.bufferedReader = br;
    }

    public Round getRound() throws IOException {

        var cards = getCards();

        var playerOneHand = getPlayerHand(cards.subList(0, 5));
        var playerTwoHand = getPlayerHand(cards.subList(5, 10));

        return new Round(playerOneHand, playerTwoHand);
    }

    private List<Card> getCards() throws IOException {

        var currentLine = bufferedReader.readLine();

        return Stream.of(currentLine.split(SPLIT))
                .map(Card::new)
                .collect(Collectors.toList());
    }

    @NotNull
    private Hand getPlayerHand(List<Card> cards) {

        return new Hand(cards);
    }

    public boolean hasNext() throws IOException {

        return bufferedReader.ready();
    }
}