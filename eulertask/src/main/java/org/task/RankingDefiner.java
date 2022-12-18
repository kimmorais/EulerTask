package org.task;

import org.task.constants.RankingEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Ranking;

public class RankingDefiner {

    private RankingDefiner() {}

    public static Ranking defineRanking(Hand hand) {

        var rankingFlushOrStraight = FlushOrStraight.get(hand);
        var rankingPairOrKind = PairOrKind.get(hand);

        var rankingNonNull = getNonNull(rankingFlushOrStraight, rankingPairOrKind);

        return rankingNonNull != null ?
                rankingNonNull
                : createRanking(hand.getNCard(4));
    }

    public static Ranking getNonNull(Ranking ...items) {

        for(Ranking r : items) {

            if (r != null) {

                return r;
            }
        }

        return null;
    }

    private static Ranking createRanking(Card card) {

        return new Ranking(RankingEnum.HIGH_CARD, card);
    }
}
