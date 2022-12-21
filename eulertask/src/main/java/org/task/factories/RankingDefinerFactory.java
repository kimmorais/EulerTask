package org.task.factories;

import org.task.solution.processing.FlushOrStraight;
import org.task.solution.processing.RankingDefiner;
import org.task.solution.processing.impl.PairOrKindImpl;
import org.task.solution.processing.impl.RankingDefinerImpl;

public class RankingDefinerFactory {

    private RankingDefinerFactory() {}

    public static RankingDefiner newInstance(FlushOrStraight flushOrStraight){
        return new RankingDefinerImpl(flushOrStraight, new PairOrKindImpl());
    }
}
