package org.task.factories;

import org.task.solution.FlushOrStraight;
import org.task.solution.PairOrKind;
import org.task.solution.RankingDefiner;

public class RankingDefinerFactory {

    private RankingDefinerFactory() {}

    public static RankingDefiner newInstance(FlushOrStraight flushOrStraight){
        return new RankingDefiner(flushOrStraight, new PairOrKind());
    }
}
