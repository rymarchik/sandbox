package core.algorithms;

import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandProcessorTest {

    private CommandProcessor commandProcessor;

    @BeforeEach
    void setUp() {
        commandProcessor = new CommandProcessor();
    }

    @ParameterizedTest
    @CsvSource({
        "+, 1",
        "-, -1",
        "*, 0",
        "/, 0",
        "+-, 0",
        "+*-/, 0"
    })
    void process_validCommands(String commands, long expectedResult) throws IOException {
        var stream = new StringReader(commands);

        var result = commandProcessor.process(stream);

        assertEquals(expectedResult, result);
    }

    @Test
    void process_emptyStream() {
        var emptyStream = new StringReader("");

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> commandProcessor.process(emptyStream));

        assertEquals("No characters were read from the stream", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "@",
        "-!+",
        "+*-?"
    })
    void process_unexpectedCharacter(String commands) {
        var stream = new StringReader(commands);

        var exception = assertThrows(IllegalArgumentException.class, () -> commandProcessor.process(stream));

        assertEquals("Unexpected character %s".formatted(exception.getMessage().charAt(exception.getMessage().length() - 1)),
            exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
        "+, Result exceeded maximum value",
        "*, Multiplication result exceeded maximum value"
    })
    void process_exceedMaxValue(String commandToExceedMaxValue, String expectedExceptionMessage) {
        String commandsToReachMaxValue = "+" +
            "*".repeat(62) +
            "-*+";
        StringReader stream = new StringReader(commandsToReachMaxValue + commandToExceedMaxValue);

        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> commandProcessor.process(stream));

        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void process_exceedMinValue() {
        String commandsToReachMinValue = "-" + "*".repeat(63);
        String commandToExceedMinValue = "-";
        StringReader stream = new StringReader(commandsToReachMinValue + commandToExceedMinValue);

        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> commandProcessor.process(stream));

        assertEquals("Result exceeded minimum value", exception.getMessage());
    }
}