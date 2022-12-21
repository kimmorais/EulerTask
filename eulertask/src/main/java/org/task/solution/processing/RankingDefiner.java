package org.task.solution.processing;

import org.task.model.Hand;
import org.task.model.Ranking;

public interface RankingDefiner {

    Ranking defineRanking(Hand hand);
}
