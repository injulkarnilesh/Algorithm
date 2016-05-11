package in.algorithm.course.util;

public class RandomBoolean {

    public static boolean next() {
        return Math.random() >= 0.5 ? true : false;
    }

}
