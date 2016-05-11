package in.algorithm.course.part.one.week.two.sort.application;

import in.algorithm.course.util.ArrayUtils;

public class KnuthShuffle {

    public static void shuffle(final Comparable[] arrayToShuffle) {

        for(int i = 0; i < arrayToShuffle.length; i++) {
            final int  j = randomIndexBefore(i);
            ArrayUtils.swap(arrayToShuffle, i, j);
        }

    }

    private static int randomIndexBefore(final int max) {
        return new Double(Math.random() * max).intValue();
    }

}
