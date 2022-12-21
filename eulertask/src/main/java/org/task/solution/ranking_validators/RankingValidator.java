package org.task.solution.ranking_validators;

import org.task.model.Hand;

public interface RankingValidator {

    boolean isCompatibleWith(Hand hand);
}
