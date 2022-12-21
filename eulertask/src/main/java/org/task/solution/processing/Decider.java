package org.task.solution.processing;

import org.task.model.Round;

public interface Decider {

    boolean playerOneWins(Round round);
}
