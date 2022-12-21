package org.task.solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.task.constants.RankingEnum;
import org.task.constants.SuitEnum;
import org.task.constants.ValueEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Ranking;
import org.task.model.Round;
import org.task.solution.interfaces.impl.DeciderImpl;
import org.task.solution.interfaces.impl.RankingDefinerImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeciderImplTest {

    private Round round;

    @Mock
    private RankingDefinerImpl rankingDefiner;

    @InjectMocks
    private DeciderImpl decider;

    @BeforeEach
    void setUp() {

        round = buildRound();
    }

    @Test
    @DisplayName("Given the player 1 with a higher ranking, should return true")
    void playerOneWins_playerOneWithHigherRanking_returnTrue() {

        when(rankingDefiner.defineRanking(round.playerOneHand())).thenReturn(buildPlayerOneHigherRanking());
        when(rankingDefiner.defineRanking(round.playerTwoHand())).thenReturn(buildPlayerBaseRanking());

        var result = decider.playerOneWins(round);

        assertTrue(result);
    }

    @Test
    @DisplayName("Given the player 1 with a lower ranking, should return false")
    void playerOneWins_playerOneWithLowerRanking_returnFalse() {

        when(rankingDefiner.defineRanking(round.playerOneHand())).thenReturn(buildPlayerOneLowerRanking());
        when(rankingDefiner.defineRanking(round.playerTwoHand())).thenReturn(buildPlayerBaseRanking());

        var result = decider.playerOneWins(round);

        assertFalse(result);
    }

    @Test
    @DisplayName("Given the player 1 with a card of higher value in the ranking, should return true")
    void playerOneWins_playerOneHigherCardValue_returnTrue() {

        when(rankingDefiner.defineRanking(round.playerOneHand())).thenReturn(buildHigherCardValue());
        when(rankingDefiner.defineRanking(round.playerTwoHand())).thenReturn(buildBaseCardValue());

        var result = decider.playerOneWins(round);

        assertTrue(result);
    }

    @Test
    @DisplayName("Given the player 1 with a card of lower value in the ranking, should return false")
    void playerOneWins_playerOneLowerCardValue_returnFalse() {

        when(rankingDefiner.defineRanking(round.playerOneHand())).thenReturn(buildLowerCardValue());
        when(rankingDefiner.defineRanking(round.playerTwoHand())).thenReturn(buildBaseCardValue());

        var result = decider.playerOneWins(round);

        assertFalse(result);
    }

    @Test
    @DisplayName("Given the player 1 with a card of higher value in their hand, should return true")
    void playerOneWins_playerOneHasHigherCard_returnTrue() {

        var round = buildDeciderRound(buildFullHandWithHigherCard(), buildFullHandWithLowerCard());

        when(rankingDefiner.defineRanking(round.playerOneHand())).thenReturn(buildBaseCardValue());
        when(rankingDefiner.defineRanking(round.playerTwoHand())).thenReturn(buildBaseCardValue());

        var result = decider.playerOneWins(round);

        assertTrue(result);
    }

    @Test
    @DisplayName("Given the player 1 with a card of lower value in their hand, should return false")
    void playerOneWins_playerOneHasLowerCard_returnFalse() {

        var round = buildDeciderRound(buildFullHandWithLowerCard(), buildFullHandWithHigherCard());

        when(rankingDefiner.defineRanking(round.playerOneHand())).thenReturn(buildBaseCardValue());
        when(rankingDefiner.defineRanking(round.playerTwoHand())).thenReturn(buildBaseCardValue());

        var result = decider.playerOneWins(round);

        assertFalse(result);
    }

    private Round buildRound() {

        return new Round(
                buildHand(),
                buildHand()
        );
    }

    private Hand buildHand() {

        return new Hand(List.of());
    }

    private Ranking buildPlayerOneHigherRanking() {

        return new Ranking(RankingEnum.ROYAL_FLUSH, buildCard());
    }

    private Card buildCard() {

        return new Card(ValueEnum.ACE, SuitEnum.HEARTS);
    }

    private Ranking buildPlayerBaseRanking() {

        return new Ranking(RankingEnum.STRAIGHT, buildCard());
    }

    private Ranking buildPlayerOneLowerRanking() {

        return new Ranking(RankingEnum.PAIR, buildCard());
    }

    private Ranking buildHigherCardValue() {

        return new Ranking(RankingEnum.STRAIGHT, buildHigherCard());
    }

    private Ranking buildBaseCardValue() {

        return new Ranking(RankingEnum.STRAIGHT, buildBaseCard());
    }

    private Ranking buildLowerCardValue() {

        return new Ranking(RankingEnum.STRAIGHT, buildLowerCard());
    }

    private Card buildHigherCard() {

        return new Card(ValueEnum.ACE, SuitEnum.HEARTS);
    }

    private Card buildBaseCard() {

        return new Card(ValueEnum.EIGHT, SuitEnum.HEARTS);
    }

    private Card buildLowerCard() {

        return new Card(ValueEnum.FOUR, SuitEnum.HEARTS);
    }

    private Round buildDeciderRound(Hand playerOneHand, Hand playerTwoHand) {

        return new Round(playerOneHand, playerTwoHand);
    }

    private Hand buildFullHandWithHigherCard() {

        var listOfHigherCard = new ArrayList<Card>();

        listOfHigherCard.add(buildHigherCard());
        listOfHigherCard.add(buildHigherCard());
        listOfHigherCard.add(buildHigherCard());
        listOfHigherCard.add(buildHigherCard());
        listOfHigherCard.add(buildHigherCard());

        return new Hand(listOfHigherCard);
    }

    private Hand buildFullHandWithLowerCard() {

        var listOfLowerCard = new ArrayList<Card>();

        listOfLowerCard.add(buildLowerCard());
        listOfLowerCard.add(buildLowerCard());
        listOfLowerCard.add(buildLowerCard());
        listOfLowerCard.add(buildLowerCard());
        listOfLowerCard.add(buildLowerCard());

        return new Hand(listOfLowerCard);
    }
}