package inventory.service;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepository;
import inventory.validator.ValidatorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class SR_IntegrationTest {

    Product product;

    private InventoryService service;

    @BeforeEach
    void setUp() {
        InventoryRepository repo= new InventoryRepository();
        this.service = new InventoryService(repo);
        product = mock(Product.class);
    }

    @Test
    public void addProductValid(){
        Mockito.when(product.getId()).thenReturn(1);
        Mockito.when(product.getName()).thenReturn("Produs 1");
        Mockito.when(product.getPrice()).thenReturn(5.0);
        ObservableList<Part> lista = FXCollections.observableArrayList();
        lista.add(new InhousePart(10, "part1", 1, 5, 3, 10, 1000));
        lista.add(new InhousePart(10, "part2", 1, 5, 3, 10, 1000));
        try {
            service.addProduct("Produs 1", 5, 15, 10, 25, lista);
            Product insertedProduct = service.lookupProduct("Produs 1");
            assert insertedProduct.getId() == product.getId();
            assert insertedProduct.getName() == product.getName();
            assert insertedProduct.getPrice() == product.getPrice();
            service.deleteProduct(service.lookupProduct("Produs 1"));
        } catch (ValidatorException ex) {
            assert false;
        }
    }

    @Test
    public void deleteproduct(){
        Mockito.when(product.getId()).thenReturn(1);
        ObservableList<Part> lista = FXCollections.observableArrayList();
        lista.add(new InhousePart(10, "part1", 0, 5, 3, 10, 1000));
        lista.add(new InhousePart(10, "part2", 0, 5, 3, 10, 1000));

        try {
            service.addProduct("Produs 2", 5, 15, 10, 25, lista);
            assert service.getNumberOfProducts() == 1;
            service.deleteProductById(product);
            assert service.getNumberOfProducts() == 0;
        } catch (ValidatorException ex) {
           assert false;
        }
    }
}
