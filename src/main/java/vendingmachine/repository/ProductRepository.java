package vendingmachine.repository;

import vendingmachine.domain.Product;
import vendingmachine.domain.ProductMaker;

import java.util.List;

public class ProductRepository {

    private final List<Product> products;

    public ProductRepository(String inputProducts) {
        products = setProducts(inputProducts);
    }

    private List<Product> setProducts(String inputProducts) {
        ProductMaker productMaker = new ProductMaker();
        return productMaker.makeProduct(inputProducts);
    }

    public boolean availableToPurchase(int insertedMoney) {
        return products.stream()
                .anyMatch(product -> product.canBuy(insertedMoney));
    }

    public int purchaseProductAndGetChanges(String productName, int insertedMoney) {
        Product product = findProduct(productName);
        checkCanPurchase(product, insertedMoney);
        return buy(product, insertedMoney);
    }

    private Product findProduct(String productName) {
        return products.stream()
                .filter(product -> product.isSameName(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 상품명입니다."));
    }

    private void checkCanPurchase(Product product, int insertedMoney) {
        if (!product.canBuy(insertedMoney)) {
            throw new IllegalArgumentException("[ERROR] 보유 금액으로 해당 상품을 구매할 수 없습니다.");
        }
    }

    private int buy(Product product, int insertedMoney) {
        return product.purchaseAndGetRemainMoney(insertedMoney);
    }
}
