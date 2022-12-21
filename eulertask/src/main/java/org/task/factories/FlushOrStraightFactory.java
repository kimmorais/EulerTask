package org.task.factories;

import org.task.solution.processing.FlushOrStraight;
import org.task.solution.processing.impl.FlushOrStraightImpl;
import org.task.solution.ranking_validators.flush.impl.FlushValidatorImpl;
import org.task.solution.ranking_validators.royal.impl.RoyalValidatorImpl;
import org.task.solution.ranking_validators.straight.impl.StraightValidatorImpl;

public class FlushOrStraightFactory {

    private FlushOrStraightFactory() {}

    public static FlushOrStraight newInstance() {

        return new FlushOrStraightImpl(new FlushValidatorImpl(), new StraightValidatorImpl(), new RoyalValidatorImpl());
    }
}
