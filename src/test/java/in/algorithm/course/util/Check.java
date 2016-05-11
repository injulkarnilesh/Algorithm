package in.algorithm.course.util;

public class Check {

    public static boolean isSorted(final Comparable [] arrayToCheck) {
        for (int i = 0; i < arrayToCheck.length - 1; i++) {
            if (arrayToCheck[i].compareTo(arrayToCheck[i+1]) > 0) {
                return false;
            }
        }
        return true;
    }
}
