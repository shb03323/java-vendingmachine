package vendingmachine.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductMaker {

    public List<Product> makeProduct(String input) {
        List<String> parsedProducts = parseProduct(input);
        return parsedProducts.stream()
                .map(this::changeToProduct)
                .collect(Collectors.toList());
    }

    private List<String> parseProduct(String input) {
        return Stream.of(input.split(";"))
                .map(product -> product.substring(1, product.length() - 1))
                .collect(Collectors.toList());
    }

    private Product changeToProduct(String input) {
        List<String> productElements = List.of(input.split(","));
        String name = productElements.get(0);
        int cost = Integer.parseInt(productElements.get(1));
        int count = Integer.parseInt(productElements.get(2));
        return new Product(name, cost, count);
    }
}
