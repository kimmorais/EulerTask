package org.task.solution.interfaces;

import org.task.model.Hand;
import org.task.model.Ranking;

import java.util.Optional;

public interface PairOrKind {

    Optional<Ranking> get(Hand hand);
}
