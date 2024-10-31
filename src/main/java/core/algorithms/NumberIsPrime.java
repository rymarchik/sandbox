package core.algorithms;

public class NumberIsPrime {

    // Простой алгоритм
    public static boolean isPrime_simple(int number) {
        if (number < 2) {
            return false;
        }

        // Условие с квадратным корнем здесь с целью сэкономить кол-во проверок. Например, возьмем число 144 и разобьем его:
        // 2*72, 3*48, 4*36, 5*28.8, 6*24, 7*20.57, 8*18, 9*16, 10*14.4, 11*13.1, 12*12.
        // И если дальше продолжать, то нужно будет брать число 13 и умножать его уже на число, меньшее 12, чтобы получить 144.
        // А все целые числа, меньшие 12, мы уже проверили.
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    // Оптимизированный алгоритм с шагом 6
    public static boolean isPrime_complex(int number) {
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        // Самая важная вещь, которую здесь надо знать - это что любое простое число больше 3 можно представить в виде 6k±1.
        // Отсюда взялось инитное число 5 (6k-1) и дальнейшая проверка числа 7 (6k+1). Шаг в 6 тоже сразу становится понятен.
        // Проверка начинается с 25, потому что до этого все числа не кратные 2 и 3 - простые, без необходимости проверять.
        // Начиная с 121 (11*11), k становится равным 2 и добавляются делители 11 (6k-1) и 13 (6k+1).
        // Потом 289 (17*17) с делителями 17 и 19, 529 (23*23), 841 (29*29). Для чисел больше 841 нужно будет проверять
        // делители 5, 7, 11, 13, 17, 19, 23, 25 (хотя тут конечно проверка не нужна), 29, 31.
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
