package org.task.solution;

import org.task.constants.RankingEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Ranking;

import java.util.Optional;
import java.util.stream.Stream;

public class RankingDefiner {

    private final FlushOrStraight flushOrStraight;
    private final PairOrKind pairOrKind;

    public RankingDefiner(FlushOrStraight flushOrStraight, PairOrKind pairOrKind) {

        this.flushOrStraight = flushOrStraight;
        this.pairOrKind = pairOrKind;
    }

    public Ranking defineRanking(Hand hand) {

        return this.getNonNullRankingOutta(hand).orElseGet(() -> createRankingWith(hand.getHigherCard()));
    }

    private Optional<Ranking> getNonNullRankingOutta(Hand hand) {
        return Stream.of(this.flushOrStraight.get(hand), this.pairOrKind.get(hand))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    private Ranking createRankingWith(Card card) {

        return new Ranking(RankingEnum.HIGH_CARD, card);
    }
}
