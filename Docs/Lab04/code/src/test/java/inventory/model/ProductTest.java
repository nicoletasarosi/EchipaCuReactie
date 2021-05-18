package inventory.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// JUnit tests
class ProductTest {

    @Test
    public void createNonValidProduct() {
        int id = 100;
        String name = "product100";
        double price = 15.02;
        int inStock = 10;
        int min = 5;
        int max = 15;
        ObservableList<Part> partList = FXCollections.observableArrayList();

        Product product = new Product(id, name, price, inStock, min, max, partList);

        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(inStock, product.getInStock());
        assertEquals(min, product.getMin());
        assertEquals(max, product.getMax());
        assertEquals(partList, product.getAssociatedParts());

        List<ProductErrorMessage> expectedErrors = new ArrayList<>();
        expectedErrors.add(ProductErrorMessage.EMPTY_PART_LIST);
        List<ProductErrorMessage> errors = Product.isValidProduct(product.getName(), product.getPrice(),
                product.getInStock(), product.getMin(), product.getMax(), product.getAssociatedParts());
        assertEquals(expectedErrors, errors);
    }

    @Test
    public void createValidProduct() {
        int id = 100;
        String name = "product101";
        double price = 100;
        int inStock = 10;
        int min = 5;
        int max = 15;
        ObservableList<Part> partList = FXCollections.observableArrayList();
        partList.add(new InhousePart(10, name, 20, inStock, min, max, 1000));
        partList.add(new InhousePart(20, name, 50, inStock, min, max, 1000));

        Product product = new Product(id, name, price, inStock, min, max, partList);

        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(inStock, product.getInStock());
        assertEquals(min, product.getMin());
        assertEquals(max, product.getMax());
        assertEquals(partList, product.getAssociatedParts());

        List<ProductErrorMessage> errors = Product.isValidProduct(product.getName(), product.getPrice(),
                product.getInStock(), product.getMin(), product.getMax(), product.getAssociatedParts());
        assertTrue(errors.isEmpty());
    }
}