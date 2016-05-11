package in.algorithm.course.util;

public class RandomInt {

    public static int next() {
        return new Double(Math.random() * Integer.MAX_VALUE).intValue();
    }

    public static int next(int max) {
        return new Double(Math.random() * max).intValue();
    }

    public static int next(int min, int max) {
        return new Double(min + ((max - min) * Math.random())).intValue();
    }

}
