package org.task;

import org.junit.jupiter.api.AfterEach;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RankingDefinerTest {

    private Hand hand;

    @Mock
    private FlushOrStraight flushOrStraight;

    @Mock
    private PairOrKind pairOrKind;

    @InjectMocks
    private RankingDefiner rankingDefiner;

    @BeforeEach
    void setUp() {

        rankingDefiner = new RankingDefiner(flushOrStraight, pairOrKind);
        hand = buildHand();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Given a non-null return of flushOrStraight, should return Ranking object with the enum of choice inside")
    void defineRanking_flushOrStraight_returnStraightFlushRanking() {

        var straightFlushRanking = createStraightFlushRanking();

        when(flushOrStraight.get(hand)).thenReturn(straightFlushRanking);
        when(pairOrKind.get(hand)).thenReturn(null);

        var result = rankingDefiner.defineRanking(hand);

        assertEquals(result, straightFlushRanking);
    }

    @Test
    @DisplayName("Given a non-null return of pairOrKind, should return Ranking object with the enum of choice inside")
    void defineHanking_pairOrKind_returnThreeOfAKindRanking() {

        var threeOfAKindRanking = createThreeOfAKindRanking();

        when(flushOrStraight.get(hand)).thenReturn(null);
        when(pairOrKind.get(hand)).thenReturn(threeOfAKindRanking);

        var result = rankingDefiner.defineRanking(hand);

        assertEquals(result, threeOfAKindRanking);
    }

    @Test
    @DisplayName("Given both flushOrStraight and pairOrKind with a null return, should return Ranking object with HIGH_CARD enum inside")
    void defineRanking_highCard_returnHighCardRanking() {

        var highCardRanking = createHighCardRanking();

        when(flushOrStraight.get(hand)).thenReturn(null);
        when(pairOrKind.get(hand)).thenReturn(null);

        var result = rankingDefiner.defineRanking(hand);

        assertEquals(result, highCardRanking);
    }

    private Hand buildHand() {

        return new Hand(getCards());
    }

    private List<Card> getCards() {

        var cards = new ArrayList<Card>();

        cards.add(buildCard());
        cards.add(buildCard());
        cards.add(buildCard());
        cards.add(buildCard());
        cards.add(buildCard());

        return cards;
    }

    private Card buildCard() {

        return new Card(ValueEnum.ACE, SuitEnum.CLUBS);
    }

    private Ranking createStraightFlushRanking() {

        return new Ranking(RankingEnum.STRAIGHT_FLUSH, buildCard());
    }

    private Ranking createThreeOfAKindRanking() {

        return new Ranking(RankingEnum.THREE_OF_A_KIND, buildCard());
    }

    private Ranking createHighCardRanking() {

        return new Ranking(RankingEnum.HIGH_CARD, buildCard());
    }
}