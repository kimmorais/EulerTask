package org.task;

import org.task.constants.RankingEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Ranking;

public class RankingDefiner {

    private final FlushOrStraight flushOrStraight;
    private final PairOrKind pairOrKind;

    public RankingDefiner() {

        this(new FlushOrStraight(), new PairOrKind());
    }

    public RankingDefiner(FlushOrStraight flushOrStraight, PairOrKind pairOrKind) {

        this.flushOrStraight = flushOrStraight;
        this.pairOrKind = pairOrKind;
    }

    public Ranking defineRanking(Hand hand) {

        var rankingFlushOrStraight = flushOrStraight.get(hand);
        var rankingPairOrKind = pairOrKind.get(hand);

        var rankingNonNull = getNonNull(rankingFlushOrStraight, rankingPairOrKind);

        return rankingNonNull != null ?
                rankingNonNull
                : createRanking(hand.getNCard(4));
    }

    private Ranking getNonNull(Ranking ...items) {

        for(Ranking r : items) {

            if (r != null) {

                return r;
            }
        }

        return null;
    }

    private Ranking createRanking(Card card) {

        return new Ranking(RankingEnum.HIGH_CARD, card);
    }
}
