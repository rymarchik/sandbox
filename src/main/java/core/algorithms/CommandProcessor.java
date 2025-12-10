package core.algorithms;

import java.io.IOException;
import java.io.Reader;

/**
 * Utility class that processes a character stream of arithmetic commands.
 *
 * Each character in the input stream represents a simple operation on a long value:
 * - '+' → increment result by 1
 * - '-' → decrement result by 1
 * - '*' → multiply result by 2
 * - '/' → divide result by 2 (integer division)
 *
 * The result starts at 0 and is modified sequentially as characters are read.
 *
 * Throws:
 * - {@code ArithmeticException} if the result overflows or underflows during operations.
 * - {@code IllegalArgumentException} if an unknown character is encountered.
 * - {@code IllegalStateException} if the stream contains no characters.
 *
 * Example input stream: "+*+/" → result becomes 1 → 2 → 3 → 1
 */
public class CommandProcessor {

    public long process(Reader stream) throws IOException {
        long result = 0;
        boolean hasReadAnyChar = false;
        int character;
        while ((character = stream.read()) != -1) {
            hasReadAnyChar = true;
            char c = (char) character;
            switch (c) {
                case '+' -> {
                    if (result == Long.MAX_VALUE) {
                        throw new ArithmeticException("Result exceeded maximum value");
                    }
                    result++;
                }
                case '-' -> {
                    if (result == Long.MIN_VALUE) {
                        throw new ArithmeticException("Result exceeded minimum value");
                    }
                    result--;
                }
                case '*' -> {
                    if (result > Long.MAX_VALUE / 2) {
                        throw new ArithmeticException("Multiplication result exceeded maximum value");
                    }
                    result *= 2;
                }
                case '/' -> result /= 2;
                default -> throw new IllegalArgumentException("Unexpected character " + c);
            }
        }
        if (!hasReadAnyChar) {
            throw new IllegalStateException("No characters were read from the stream");
        }
        return result;
    }
}
