package core.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for checking whether a number or a string is a palindrome.
 *
 * <p>A palindrome is a value that reads the same forward and backward.
 *
 * <p>Supports:<br>
 * - Integer input: checks if the number's digits form a palindrome.<br>
 * - String input: checks if the characters form a palindrome (case-sensitive).
 *
 * <p>Example:<br>
 * - checkIfPalindrome(12321) → true <br>
 * - checkIfPalindrome("racecar") → true <br>
 * - checkIfPalindrome("hello") → false
 */
public class Palindrome {

    public static boolean checkIfPalindromeIterate(int number) {
        if (number < 0) {
            return false;
        }
        if (number == 0) {
            return true;
        }

        int original = number;
        int reversed = 0;

        while (number > 0) {
            int digit = number % 10;
            reversed = reversed * 10 + digit;
            number /= 10;
        }
        return original == reversed;
    }

    // Неэффективное решение
    public static boolean checkIfPalindromeRecursion(int number) {
        List<Integer> numberList = new ArrayList<>();

        recursion(number, numberList);

        for (int i = 0; i < numberList.size() / 2; i++) {
            if (!numberList.get(i).equals(numberList.get(numberList.size() - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIfPalindromeIterate(String s) {
        s = filter(s);
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIfPalindromeRecursion(String s) {
        s = filter(s);
        return recursion(s, 0, s.length() - 1);
    }

    // Лучше, т.к. фильтр и проверка в одной итерации
    public static boolean checkIfPalindromeEfficient(String s) {
        if (s == null) {
            return true;
        }

        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);

            // Пропускаем символы, которые не являются буквами, слева
            if (!Character.isLetter(startChar)) {
                start++;
            }
            // Пропускаем символы, которые не являются буквами, справа
            else if (!Character.isLetter(endChar)) {
                end--;
            }
            // Сравниваем только буквы
            else {
                if (Character.toLowerCase(startChar) != Character.toLowerCase(endChar)) {
                    return false;
                }
                start++;
                end--;
            }
        }
        return true;
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

    private static boolean recursion(String s, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (s.charAt(start) != s.charAt(end)) {
            return false;
        }
        return recursion(s, start + 1, end - 1);
    }

    private static String filter(String s) {
        // Не производительный, но функциональный и чистый (или если забыл как пользоваться regex)
        List<Character> list = s.codePoints()
            .filter(Character::isAlphabetic)
            .mapToObj(c -> (char) c)
            .map(Character::toLowerCase)
            .toList();

        // Самый быстрый и компактный
        String clean = s.replaceAll("[^a-zA-Z]", "");
        clean = clean.toLowerCase();

        return clean;
    }

    public static void main(String[] args) {
        int num = 12345321;
        String str = "race a ecar";

        System.out.println(checkIfPalindromeIterate(num));
        System.out.println(checkIfPalindromeRecursion(num));

        System.out.println(checkIfPalindromeEfficient(str));
        System.out.println(checkIfPalindromeIterate(str));
        System.out.println(checkIfPalindromeRecursion(str));
    }
}
