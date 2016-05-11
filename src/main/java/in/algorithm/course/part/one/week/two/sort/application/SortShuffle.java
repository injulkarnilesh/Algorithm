package in.algorithm.course.part.one.week.two.sort.application;

import in.algorithm.course.part.one.week.two.sort.SelectionSort;

import java.util.HashMap;
import java.util.Map;

public class SortShuffle {


    public static void shuffle(final Integer [] arrayToShuffle) {
        Map<Double, Integer> randomIndexToNumber = new HashMap();
        final Double [] randomIndices = new Double[arrayToShuffle.length];

        for(int i = 0; i < arrayToShuffle.length; i++) {
            randomIndexToNumber.put(randomIndices[i] = Math.random(), arrayToShuffle[i]);
        }

        SelectionSort.sort(randomIndices);

        for(int i = 0; i < arrayToShuffle.length; i++) {
            arrayToShuffle[i] = randomIndexToNumber.get(randomIndices[i]);
        }
    }
}
