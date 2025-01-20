package core.algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UniqueIntegers {

    public static void findUniqueIntegers() {
        int[] array = {-1, -2, 3, 3, 5, -2};

        findUniqueIntegers(array).forEach(System.out::println);
    }

    public static List<Integer> findUniqueIntegers(int[] array) {
        return Arrays.stream(array)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() == 1L)
            .map(Map.Entry::getKey)
            .toList();
    }
}
