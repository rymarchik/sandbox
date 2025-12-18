package core.algorithms;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringCounter {

    private static final int TOP_NAMES = 2;

    public static List<String> register() {
        return List.of("Billy", "Monica", "Jenny", "Billy", "Jenny", "Billy");
    }

    public static List<Map.Entry<String, Long>> retrieveTopOccurrences(List<String> names) {
        return names.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(TOP_NAMES)
            .toList();
    }

    public static void main(String[] args) {
        List<String> names = register();
        List<Map.Entry<String, Long>> topOccurrencesMap = retrieveTopOccurrences(names);
        System.out.println(topOccurrencesMap);
    }
}

