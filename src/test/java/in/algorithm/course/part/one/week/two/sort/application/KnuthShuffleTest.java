package in.algorithm.course.part.one.week.two.sort.application;

import in.algorithm.course.util.RandomInt;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class KnuthShuffleTest {

    @Test
    public void shouldShuffleArrayOfInt() throws Exception {
        final int size = RandomInt.next(100, 1000);
        final Integer [] arrayToShuffle = new Integer[size];
        for (int i = 0; i < size; i++ ) {
            arrayToShuffle[i] = RandomInt.next();
        }

        final Integer [] originalCopy = Arrays.copyOf(arrayToShuffle, arrayToShuffle.length);

        KnuthShuffle.shuffle(arrayToShuffle);

        int noOfPositionsNotShuffled = 0;
        for(int i = 0; i < arrayToShuffle.length; i++) {
            if(arrayToShuffle[i] == originalCopy[i]) {
                noOfPositionsNotShuffled++;
            }
        }

        Assert.assertTrue(noOfPositionsNotShuffled < arrayToShuffle.length/4);
    }
}
