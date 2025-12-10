package core.algorithms.visa;

/**
 * You are given two strings: pattern and source. The first string pattern contains only the symbols 0 and 1, and the second
 * string source contains only lowercase English letters.
 * <p>Your task is to calculate the number of substrings of source that match pattern.
 *
 * <p>We’ll say that a substring source[l..r] matches pattern if the following three conditions are met:
 * <p>– The pattern and substring are equal in length.
 * <p>– Where there is a 0 in the pattern, there is a vowel in the substring.
 * <p>– Where there is a 1 in the pattern, there is a consonant in the substring.
 * <p>Vowels are ‘a‘, ‘e‘, ‘i‘, ‘o‘, ‘u‘, and ‘y‘. All other letters are consonants.
 *
 * <p><h3>Example</h3>
 *
 * <p>For pattern = "010" and source = "amazing", the output should be solution(pattern, source) = 2.
 * <p>– “010” matches source[0..2] = "ama". The pattern specifies “vowel, consonant, vowel”. “ama” matches this pattern: 0
 * matches a, 1 matches m, and 0 matches a.
 * <p>– “010” doesn’t match source[1..3] = "maz"
 * <p>– “010” matches source[2..4] = "azi"
 * <p>– “010” doesn’t match source[3..5] = "zin"
 * <p>– “010” doesn’t match source[4..6] = "ing"
 */
public class StringPatternMatcher {

    // O(N*M)
    private static int solution(String pattern, String source) {
        int patternLen = pattern.length();
        int sourceLen = source.length();
        int matchCount = 0;

        if (patternLen > sourceLen) {
            return 0;
        }

        outer:
        for (int i = 0; i <= source.length() - pattern.length(); i++) {
            for (int k = 0; k < pattern.length(); k++) {
                char patternChar = pattern.charAt(k);
                char sourceChar = source.charAt(i + k);

                boolean patternExpectsVowel = (patternChar == '0');
                boolean sourceIsVowel = isVowel(sourceChar);

                if (patternExpectsVowel != sourceIsVowel) {
                    continue outer;
                }
            }
            matchCount++;
        }
        return matchCount;
    }

    private static boolean isVowel(char c) {
        return "aeiouy".indexOf(c) != -1;
    }

    public static void testCase1() {
        System.out.println(solution("010", "amazing"));
    }

    public static void testCase2() {
        System.out.println(solution("100", "codesignal"));
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
    }
}
