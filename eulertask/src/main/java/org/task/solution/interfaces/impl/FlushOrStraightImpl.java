package org.task.solution.interfaces.impl;

import org.task.constants.RankingEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Ranking;
import org.task.solution.interfaces.FlushOrStraight;
import org.task.solution.ranking_validators.flush.FlushValidator;
import org.task.solution.ranking_validators.royal.RoyalValidator;
import org.task.solution.ranking_validators.straight.StraightValidator;

import java.util.Optional;

public class FlushOrStraightImpl implements FlushOrStraight {

    private final FlushValidator flushValidator;
    private final StraightValidator straightValidator;
    private final RoyalValidator royalValidator;

    public FlushOrStraightImpl(FlushValidator flushValidator, StraightValidator straightValidator, RoyalValidator royalValidator) {

        this.flushValidator = flushValidator;
        this.straightValidator = straightValidator;
        this.royalValidator = royalValidator;
    }

    @Override
    public Optional<Ranking> get(Hand hand) {

        var flush = this.flushValidator.isCompatibleWith(hand);
        var straight = this.straightValidator.isCompatibleWith(hand);
        var royal = this.royalValidator.isCompatibleWith(hand);

        if (flush) {

            if (royal) {
                return Optional.of(createRanking(RankingEnum.ROYAL_FLUSH, hand.getHigherCard()));
            }
            if (straight) {
                return Optional.of(createRanking(RankingEnum.STRAIGHT_FLUSH, hand.getHigherCard()));
            }

            return Optional.of(createRanking(RankingEnum.FLUSH, hand.getHigherCard()));
        }

        if (straight) {

            return Optional.of(createRanking(RankingEnum.STRAIGHT, hand.getHigherCard()));
        }

        return Optional.empty();
    }

    private Ranking createRanking(RankingEnum rankingEnum, Card card) {

        return new Ranking(rankingEnum, card);
    }
}
