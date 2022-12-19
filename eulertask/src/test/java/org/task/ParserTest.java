package org.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.task.constants.SuitEnum;
import org.task.constants.ValueEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Round;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParserTest {

    private static final String CARDS = "AC KH QS AH 8D AC KH QS AH 8D";

    @Mock
    private BufferedReader bufferedReader;

    @InjectMocks
    private Parser parser;

    @Test
    @DisplayName("Given a string of cards, should perform parsing and return Round object")
    void getRound_givenAStringOfCards_returnRound() throws IOException {

        var expectedRound = buildRound();

        when(bufferedReader.readLine()).thenReturn(CARDS);

        var result = parser.getRound();

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedRound);
    }

    @Test
    @DisplayName("Given ready bufferedReader, should return true")
    void hasNext_bufferedReaderIsReady_returnTrue() throws IOException {

        when(bufferedReader.ready()).thenReturn(true);

        var result = parser.hasNext();

        assertTrue(result);
    }

    @Test
    @DisplayName("Given not ready bufferedReader, should return true")
    void hasNext_bufferedReaderIsNotReady_returnFalse() throws IOException {

        when(bufferedReader.ready()).thenReturn(false);

        var result = parser.hasNext();

        assertFalse(result);
    }

    private Round buildRound() {

        return new Round(getPlayerOneHand(), getPlayerTwoHand());
    }

    private Hand getPlayerOneHand() {

        return new Hand(getCards().subList(0, 5));
    }

    private Hand getPlayerTwoHand() {

        return new Hand(getCards().subList(5, 10));
    }

    private List<Card> getCards() {

        return List.of(
                getCard(ValueEnum.ACE, SuitEnum.CLUBS),
                getCard(ValueEnum.KING, SuitEnum.HEARTS),
                getCard(ValueEnum.QUEEN, SuitEnum.SPADES),
                getCard(ValueEnum.ACE, SuitEnum.HEARTS),
                getCard(ValueEnum.EIGHT, SuitEnum.DIAMONDS),
                getCard(ValueEnum.ACE, SuitEnum.CLUBS),
                getCard(ValueEnum.KING, SuitEnum.HEARTS),
                getCard(ValueEnum.QUEEN, SuitEnum.SPADES),
                getCard(ValueEnum.ACE, SuitEnum.HEARTS),
                getCard(ValueEnum.EIGHT, SuitEnum.DIAMONDS)
        );
    }

    private Card getCard(ValueEnum valueEnum, SuitEnum suitEnum) {

        return new Card(valueEnum, suitEnum);
    }
}