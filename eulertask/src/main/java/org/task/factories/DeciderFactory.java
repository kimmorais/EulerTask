package org.task.factories;

import org.task.solution.processing.Decider;
import org.task.solution.processing.RankingDefiner;
import org.task.solution.processing.impl.DeciderImpl;

public class DeciderFactory {

    private DeciderFactory() {}

    public static Decider newInstance(RankingDefiner rankingDefiner) {

        return new DeciderImpl(rankingDefiner);
    }
}
