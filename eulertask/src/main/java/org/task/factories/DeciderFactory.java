package org.task.factories;

import org.task.solution.interfaces.Decider;
import org.task.solution.interfaces.RankingDefiner;
import org.task.solution.interfaces.impl.DeciderImpl;

public class DeciderFactory {

    private DeciderFactory() {}

    public static Decider newInstance(RankingDefiner rankingDefiner) {

        return new DeciderImpl(rankingDefiner);
    }
}
