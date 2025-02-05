package core.exercise.shopping;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<Product, Integer> products = new HashMap<>();

    public void addProductToCart(Product product) {
        products.put(product, products.getOrDefault(product, 0) + 1);
    }

    public void removeProductFromCart(Product product) {
        if (products.containsKey(product)) {
            int count = products.get(product);
            if (count > 1) {
                products.put(product, count - 1);
            }
            else {
                products.remove(product);
            }
        }
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public int getTotalPrice() {
        if (isEmpty()) {
            throw new RuntimeException("Корзина пуста");
        }
        return products.entrySet().stream()
            .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
            .sum();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int applyDiscount(int discountPercentage) {
        int totalPrice = getTotalPrice();
        return totalPrice - (totalPrice * discountPercentage / 100);
    }
}
