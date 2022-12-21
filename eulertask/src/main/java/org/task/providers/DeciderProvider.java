package org.task.providers;

import org.task.factories.DeciderFactory;
import org.task.factories.FlushOrStraightFactory;
import org.task.factories.RankingDefinerFactory;
import org.task.solution.processing.Decider;

public class DeciderProvider {

    private DeciderProvider(){}

    public static Decider provideInstance() {

        return DeciderFactory.newInstance(RankingDefinerFactory.newInstance(FlushOrStraightFactory.newInstance()));
    }
}
