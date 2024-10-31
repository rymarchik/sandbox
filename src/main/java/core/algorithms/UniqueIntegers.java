package core.algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UniqueIntegers {

    public static List<Integer> findUniqueIntegers() {
        int[] array = {-1, -2, 3, 3, -2};

        return findUniqueIntegers(array);
    }

    public static List<Integer> findUniqueIntegers(int[] array) {
        // Count the frequency of each element
        Map<Integer, Long> frequencyMap = Arrays.stream(array)
            .boxed()
            .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        // Filter elements that have a frequency of one
        return frequencyMap.entrySet().stream()
            .filter(entry -> entry.getValue() == 1)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }
}
