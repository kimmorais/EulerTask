package org.task.solution.processing.impl;

import org.task.constants.RankingEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Ranking;
import org.task.solution.processing.FlushOrStraight;
import org.task.solution.processing.PairOrKind;
import org.task.solution.processing.RankingDefiner;

import java.util.Optional;
import java.util.stream.Stream;

public class RankingDefinerImpl implements RankingDefiner {

    private final FlushOrStraight flushOrStraight;
    private final PairOrKind pairOrKind;

    public RankingDefinerImpl(FlushOrStraight flushOrStraight, PairOrKindImpl pairOrKind) {

        this.flushOrStraight = flushOrStraight;
        this.pairOrKind = pairOrKind;
    }

    @Override
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
