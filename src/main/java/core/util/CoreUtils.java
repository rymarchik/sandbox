package core.util;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CoreUtils {

    public static double calculateFee(double amountInRub) {
        NavigableMap<Double, Double> feeRates = new TreeMap<>(Map.of(
            0.0, 0.015,
            1000.0, 0.01,
            5000.0, 0.005
        ));
        return amountInRub * feeRates.floorEntry(amountInRub).getValue();
    }

    public static void main(String[] args) {
        System.out.println(calculateFee(999));
        System.out.println(calculateFee(1000));
        System.out.println(calculateFee(3000));
        System.out.println(calculateFee(5000));
    }

}
