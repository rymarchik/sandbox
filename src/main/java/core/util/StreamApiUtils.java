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

    public static void case1() {
        List<Integer> integers = List.of(1, 2, 3, 4);
        sumIntegers(integers); // 10
    }

    public static void case2() {
        List<Integer> integers = List.of(1, 2, 3, 4);
        sumIntegersInParallel(integers); // 10
    }

    public static void case3() {
        List<Integer> integers = List.of(1, 2, 3, 4);
        sumIntegersWithNullIdentity(integers); // 10
    }

    public static void case4() {
        List<Integer> integers = List.of(1, 2, 3, 4);
        sumIntegersWithNullIdentityInParallel(integers); // 10
    }

    public static void case5() {
        List<Integer> integers = List.of(1, 2, 3, 4);
        sumIntegersWithNotNullIdentity(integers); // 15
    }

    public static void case6() {
        List<Integer> integers = List.of(1, 2, 3, 4);
        sumIntegersWithNotNullIdentityInParallel(integers); // 30
    }
}
