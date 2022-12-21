package org.task.factories;

import org.task.solution.processing.PairOrKind;
import org.task.solution.processing.impl.PairOrKindImpl;
import org.task.solution.ranking_validators.four_of_a_kind.impl.FourOfAKindValidatorImpl;
import org.task.solution.ranking_validators.full_house.impl.FullHouseValidatorImpl;
import org.task.solution.ranking_validators.pair.impl.PairValidatorImpl;
import org.task.solution.ranking_validators.three_of_a_kind.impl.ThreeOfAKindValidatorImpl;

public class PairOrKindFactory {

    private PairOrKindFactory() {}

    public static PairOrKind newInstance() {

        return new PairOrKindImpl(new FourOfAKindValidatorImpl(), new ThreeOfAKindValidatorImpl(), new FullHouseValidatorImpl(), new PairValidatorImpl());
    }
}
