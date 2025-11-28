package core.algorithms;

import java.util.Arrays;
import java.util.stream.Collectors;

// input -  "now is your moment"
// result - "won si ruoy tnemom"
public class WordReverser {

    public static String reverseWords(String input) {
        StringBuilder result = new StringBuilder(input.length());
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == ' ') {
                result.append(current.reverse()).append(' ');
                current.setLength(0); // сброс без новой аллокации
            } else {
                current.append(c);
            }
        }

        // последнее слово
        result.append(current.reverse());

        return result.toString();
    }

    // менее оптимально
    public static String reverseWordsWithSplit(String input) {
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder(input.length());

        for (int i = 0; i < words.length; i++) {
            sb.append(new StringBuilder(words[i]).reverse());
            if (i < words.length - 1) sb.append(' ');
        }

        return sb.toString();
    }

    // еще менее оптимально, но супер компактно
    public static String reverseWordsWithStream(String input) {
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
