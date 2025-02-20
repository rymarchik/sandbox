package core.algorithms;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ThirdNonRepeatedCharacter {

    public static void findThirdNonRepeatedChar() {
        String s = "aaabbbgfeedccn";
        Optional<Character> result = alternativeFindThirdNonRepeatedChar(s);

        result.ifPresentOrElse(
            c -> System.out.println("The third non-repeated character is: " + c),
            () -> System.out.println("There are less than three non-repeated characters in the string.")
        );
    }

    public static Optional<Character> findThirdNonRepeatedChar(String s) {
        // Step 1: Count the occurrences of each character using LinkedHashMap to maintain insertion order
        Map<Character, Integer> frequency = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        // Step 2 and Step 3: Use streams to find the third non-repeated character
        return frequency.entrySet().stream()
            .filter(entry -> entry.getValue() == 1) // Filter non-repeated characters
            .map(Map.Entry::getKey) // Get the character
            .skip(2) // Skip the first two non-repeated characters
            .findFirst(); // Get the third non-repeated character
    }

    public static Optional<Character> alternativeFindThirdNonRepeatedChar(String s) {
        // s.chars() предпочтительнее, чем Stream.of(s.toCharArray()), и не создает дополнительный объект (массив)
        return s.chars()
            .mapToObj(c -> (char) c) // преобразуем ASCII-коды в символы
            .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() == 1) // Filter non-repeated characters
            .map(Map.Entry::getKey) // Get the character
            .skip(2) // Skip the first two non-repeated characters
            .findFirst(); // Get the third non-repeated character
    }
}
