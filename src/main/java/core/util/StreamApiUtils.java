package core.util;

import java.util.List;

public class StreamApiUtils {

    public static void sumIntegers(List<Integer> integers) {
        integers.stream()
            .reduce(Integer::sum)
            .ifPresent(System.out::println);
    }

    public static void sumIntegersInParallel(List<Integer> integers) {
        integers.parallelStream()
            .reduce(Integer::sum)
            .ifPresent(System.out::println);
    }

    public static void sumIntegersWithNullIdentity(List<Integer> integers) {
        System.out.println(integers.stream()
            .reduce(0, Integer::sum));
    }

    public static void sumIntegersWithNullIdentityInParallel(List<Integer> integers) {
        System.out.println(integers.parallelStream()
            .reduce(0, Integer::sum));
    }

    public static void sumIntegersWithNotNullIdentity(List<Integer> integers) {
        System.out.println(integers.stream()
            .reduce(5, Integer::sum));
    }

    public static void sumIntegersWithNotNullIdentityInParallel(List<Integer> integers) {
        System.out.println(integers.parallelStream()
            .reduce(5, Integer::sum));
    }

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4);

        sumIntegers(integers); // 10
        sumIntegersInParallel(integers); // 10
        sumIntegersWithNullIdentity(integers); // 10
        sumIntegersWithNullIdentityInParallel(integers); // 10
        sumIntegersWithNotNullIdentity(integers); // 15
        sumIntegersWithNotNullIdentityInParallel(integers); // 30
    }
}
