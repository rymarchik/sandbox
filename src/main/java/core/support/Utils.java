package core.support;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static boolean checkIfPalindrome(int number) {
        List<Integer> numberList = new ArrayList<>();

        recursion(number, numberList);

        for (int i = 0; i < numberList.size() / 2; i++) {
            if (!numberList.get(i).equals(numberList.get(numberList.size() -1 - i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIfPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        else {
            if (s.substring(0, 1).equals(s.substring(s.length() - 1, s.length()))) {
                if (s.length() == 2) {
                    return true;
                }
                return checkIfPalindrome(s.substring(1, s.length() - 1));
            }
            else {
                return false;
            }
        }
    }

    private static void recursion(int n, List<Integer> numberList) {
        if (n < 10) {
            numberList.add(n);
        }
        else {
            numberList.add(n % 10);
            recursion(n / 10, numberList);
        }
    }
}
