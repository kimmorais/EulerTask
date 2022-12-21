package org.task.factories;

import org.task.solution.interfaces.FlushOrStraight;
import org.task.solution.interfaces.RankingDefiner;
import org.task.solution.interfaces.impl.PairOrKindImpl;
import org.task.solution.interfaces.impl.RankingDefinerImpl;

public class RankingDefinerFactory {

    private RankingDefinerFactory() {}

    public static RankingDefiner newInstance(FlushOrStraight flushOrStraight){
        return new RankingDefinerImpl(flushOrStraight, new PairOrKindImpl());
    }
}
