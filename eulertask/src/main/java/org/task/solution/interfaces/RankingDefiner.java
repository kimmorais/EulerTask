package org.task.solution.interfaces;

import org.task.model.Hand;
import org.task.model.Ranking;

public interface RankingDefiner {

    Ranking defineRanking(Hand hand);
}
