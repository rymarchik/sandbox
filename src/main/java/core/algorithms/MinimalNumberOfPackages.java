package core.algorithms;

/**
 * A large package can hold 5 items, while a small package can hold only 1 item. The available number of both large and small
 * packages is limited. All items must be placed in packages and used packages can have empty space left.
 * Write a function that calculates the minimum number of packages needed to hold a given number of items. If it's not possible
 * to meet the requirements, return -1.
 * For example, if we have 13 items, 3 large and 10 small packages, the function should return 3 (3 large packages)
 */
public class MinimalNumberOfPackages {

    public static long minimalNumberOfPackages(long items, long availableLargePackages, long availableSmallPackages) {
        final int largePackageSize = 5;
        final int smallPackageSize = 2;

        long requiredLargePackages = (items + largePackageSize - 1) / largePackageSize;
        if (requiredLargePackages <= availableLargePackages) {
            return requiredLargePackages;
        } else {
            long remainingItems = items - availableLargePackages * largePackageSize;
            if (remainingItems > availableSmallPackages * smallPackageSize) {
                return -1;
            }
            long requiredSmallPackages = (remainingItems + smallPackageSize - 1) / smallPackageSize;
            return availableLargePackages + requiredSmallPackages;
        }
    }
}
