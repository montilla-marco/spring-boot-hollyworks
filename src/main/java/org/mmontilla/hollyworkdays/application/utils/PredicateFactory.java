package org.mmontilla.hollyworkdays.application.utils;

import java.util.function.Predicate;

public class PredicateFactory {

    public enum Integers implements Predicate<Integer> {
        YEAR( yearOf -> (yearOf > 2022 && yearOf <= 3000)),
        NUMBER_OF( yearOf -> (yearOf > 0 && yearOf <= 18));

        private final Predicate<Integer> predicate;
        Integers(Predicate<Integer> predicate) {
            this.predicate =  predicate;
        }

        @Override
        public boolean test(Integer integer) {
            return predicate.test(integer);
        }
    }

}
