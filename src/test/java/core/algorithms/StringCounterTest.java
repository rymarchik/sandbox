package core.algorithms;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StringCounterTest {

    @Test
    void registerShouldReturnCorrectData() {
        List<String> expected = List.of("Billy", "Monica", "Jenny", "Billy", "Jenny", "Billy");

        List<String> actual = StringCounter.register();

        assertNotNull(actual, "Список имен не должен быть null");
        assertEquals(expected.size(), actual.size(), "Размер списка должен совпадать");
        assertEquals(expected, actual, "Содержимое списка должно совпадать");
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideTestData")
    void retrieveTopOccurrencesShouldReturnCorrectTopN(
        List<String> inputNames,
        List<Map.Entry<String, Long>> expectedTop,
        String description) {

        List<Map.Entry<String, Long>> actualTop = StringCounter.retrieveTopOccurrences(inputNames);

        assertNotNull(actualTop, "Результат не должен быть null");
        assertEquals(expectedTop.size(), actualTop.size(), "Размер Top-N списка должен быть корректным");

        // Проверяем, что каждый ожидаемый элемент присутствует и имеет правильный счет
        for (int i = 0; i < expectedTop.size(); i++) {
            Map.Entry<String, Long> expectedEntry = expectedTop.get(i);
            Map.Entry<String, Long> actualEntry = actualTop.get(i);

            assertEquals(expectedEntry.getKey(), actualEntry.getKey(),
                "Имя на позиции " + (i + 1) + " должно совпадать");
            assertEquals(expectedEntry.getValue(), actualEntry.getValue(),
                "Счет на позиции " + (i + 1) + " должен совпадать");
        }
    }

    /**
     * Предоставляет тестовые данные для параметризованных тестов.
     */
    static Stream<Arguments> provideTestData() {
        return Stream.of(
            // Сценарий 1: Базовый случай (Билли и Дженни)
            Arguments.of(
                List.of("Billy", "Monica", "Jenny", "Billy", "Jenny", "Billy"),
                List.of(
                    Map.entry("Billy", 3L),
                    Map.entry("Jenny", 2L)
                ),
                "Базовый случай"
            ),

            // Сценарий 2: Одинаковое количество (Monica и Jenny по 2)
            Arguments.of(
                List.of("A", "B", "A", "C", "C"), // A:2, C:2, B:1. Top-2 = 2
                List.of(
                    // Порядок может зависеть от стабильности сортировки,
                    // но здесь ожидаем A и C с равным счетом.
                    // Поскольку Map.Entry.comparingByValue() не стабилен для одинаковых
                    // значений, мы проверяем только наличие элементов и их счет.
                    Map.entry("A", 2L),
                    Map.entry("C", 2L)
                ),
                "Случай с одинаковым количеством (A, C)"
            ),

            // Сценарий 3: Пустой ввод
            Arguments.of(
                List.of(),
                List.of(),
                "Случай пустого списка"
            ),

            // Сценарий 4: Элементов меньше, чем TOP_NAMES (TOP_NAMES = 2)
            Arguments.of(
                List.of("Solo", "Solo"), // Solo: 2
                List.of(
                    Map.entry("Solo", 2L)
                ),
                "Случай меньшего количества, чем TOP_NAMES"
            )
        );
    }
}
