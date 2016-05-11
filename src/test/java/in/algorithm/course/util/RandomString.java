package in.algorithm.course.util;

public class RandomString {

    private static String CHARS = "ABCDEFGHIJKLMOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private static int CHARS_SIZE = CHARS.length();

    public static String next() {
        return randomOfSize(RandomInt.next(10, 100));
    }

    private static String randomOfSize(final int size) {
        final StringBuilder random = new StringBuilder();

        for (int i = 0; i < size; i++) {
            random.append(CHARS.charAt(RandomInt.next(CHARS_SIZE)));
        }

        return random.toString();
    }

    public static String next(final int size) {
        return randomOfSize(size);
    }

}
