package core.algorithms;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

// input -  "now is your moment"
// result - "won si ruoy tnemom"
public class WordReverser {

    public static String reverseWords(String input) {
        Objects.requireNonNull(input, "Input string cannot be null");
        StringBuilder result = new StringBuilder(input.length());
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == ' ') {
                result.append(current.reverse()).append(' ');
                current.setLength(0); // сброс без новой аллокации
            }
            else {
                current.append(c);
            }
        }

        // последнее слово
        result.append(current.reverse());

        return result.toString();
    }

    // менее оптимально
    public static String reverseWordsWithSplit(String input) {
        Objects.requireNonNull(input, "Input string cannot be null");
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder(input.length());
        StringBuilder current = new StringBuilder();

        for (String word : words) {
            current.setLength(0);
            current.append(word).reverse();
            result.append(current).append(' ');
        }
        result.setLength(result.length() - 1);

        return result.toString();
    }

    // еще менее оптимально, но супер компактно
    public static String reverseWordsWithStream(String input) {
        Objects.requireNonNull(input, "Input string cannot be null");
        return Arrays.stream(input.split(" "))
            .map(word -> new StringBuilder(word).reverse())
            .collect(Collectors.joining(" "));
    }

    public static void testCase() {
        System.out.println(reverseWords("now is your moment"));
        System.out.println(reverseWordsWithSplit("string builder rocks"));
        System.out.println(reverseWordsWithStream("stream api sucks"));
    }
}
