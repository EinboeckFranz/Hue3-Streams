package net.htlgkr.feinboeck18.example3;

import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class ConditionDefiner {
    final Predicate<Integer> isEven = (integer -> integer % 2 == 0);
    final IntPredicate isPositive = (value -> value > 0);
    final Predicate<Integer> isZero = (integer -> integer==0);
    final IntPredicate isNull = (Objects::isNull);
    final Predicate<String> isShortWord = (s -> s.length() < 4);

    final IntPredicate isPositiveAndEven = isPositive.and((IntPredicate) isEven);
    final IntPredicate isPositiveAndOdd = isPositive.and((IntPredicate) isEven.negate());
}