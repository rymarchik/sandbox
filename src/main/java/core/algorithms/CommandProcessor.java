package core.algorithms;

import java.io.IOException;
import java.io.Reader;

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
