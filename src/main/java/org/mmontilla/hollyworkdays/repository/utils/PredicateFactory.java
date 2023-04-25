package org.mmontilla.hollyworkdays.repository.utils;

import java.util.function.Predicate;

public class PredicateFactory {

    public enum Integers implements Predicate<Integer> {
        YEAR( yearOf -> (yearOf > 1999 && yearOf <= 3000)),
        HOLLY_DAYS_NUMBER( numberOf -> (numberOf > 0 && numberOf <= 18));

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
