package core.exercise.shopping;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static core.exercise.shopping.ShoppingCartTest.ProductFactory.createProduct;

@ExtendWith(MockitoExtension.class)
class ShoppingCartTest {

    @InjectMocks
    private ShoppingCart shoppingCart;

    @Test
    void addProductToCart_ShouldAddProduct() {
        var kebab = createProduct("Kebab", 200);
        var moloko = createProduct("Moloko", 40);

        shoppingCart.addProductToCart(kebab);
        shoppingCart.addProductToCart(kebab);
        shoppingCart.addProductToCart(moloko);

        Map<Product, Integer> products = shoppingCart.getProducts();
        assertEquals(2, products.size());
        assertEquals(2, products.get(kebab));
    }

    @Test
    void removeProductFromCart_ShouldRemoveProduct() {
        var kebab = createProduct("Kebab", 200);
        var moloko = createProduct("Moloko", 40);

        shoppingCart.addProductToCart(kebab);
        shoppingCart.addProductToCart(kebab);
        shoppingCart.addProductToCart(moloko);

        shoppingCart.removeProductFromCart(kebab);

        Map<Product, Integer> products = shoppingCart.getProducts();
        assertEquals(2, products.size());
        assertEquals(1, products.get(kebab));
    }

    @Test
    void getTotalPrice_ShouldReturnSumOfPrices() {
        var kebab = createProduct("Kebab", 200);
        var moloko = createProduct("Moloko", 40);

        shoppingCart.addProductToCart(kebab);
        shoppingCart.addProductToCart(moloko);
        shoppingCart.addProductToCart(moloko);

        assertEquals(280, shoppingCart.getTotalPrice());
    }

    @Test
    void getTotalPrice_ShouldThrowException_WhenCartIsEmpty() {
        assertThrows(RuntimeException.class, shoppingCart::getTotalPrice);
    }

    @Test
    void getTotalPrice_ShouldThrowException_WhenCartIsEmptyAfterRemovingProducts() {
        var kebab = createProduct("Kebab", 200);

        shoppingCart.addProductToCart(kebab);
        shoppingCart.removeProductFromCart(kebab);

        assertThrows(RuntimeException.class, shoppingCart::getTotalPrice);
    }

    @Test
    void applyDiscount() {
        var kebab = createProduct("Kebab", 200);
        var moloko = createProduct("Moloko", 40);

        shoppingCart.addProductToCart(kebab);
        shoppingCart.addProductToCart(moloko);

        assertEquals(216, shoppingCart.applyDiscount(10));
    }

    static class ProductFactory {
        public static Product createProduct(String name, int price) {
            return new Product(name, price);
        }
    }
}

