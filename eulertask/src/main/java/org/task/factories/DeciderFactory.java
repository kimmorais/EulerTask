package org.task.factories;

import org.task.solution.Decider;
import org.task.solution.RankingDefiner;

public class DeciderFactory {

    private DeciderFactory() {}

    public static Decider newInstance(RankingDefiner rankingDefiner) {

        return new Decider(rankingDefiner);
    }
}
