package inventory.service;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.Product;
import inventory.model.ProductErrorMessage;
import inventory.repository.InventoryRepository;
import inventory.validator.ValidatorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class InventoryServiceTest {

    Product product;

    @Mock
    private InventoryRepository repository;

    @InjectMocks
    private InventoryService service;

    @BeforeEach
    void setUp() {
        product = mock(Product.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addProductValid(){
        Mockito.when(repository.getNumberOfProducts()).thenReturn(0);
        assert service.getNumberOfProducts() == 0;
        Mockito.doNothing().when(repository).addProduct(product);
        Mockito.when(product.getId()).thenReturn(1);
        Mockito.when(product.getName()).thenReturn("Produs 1");
        Mockito.when(product.getPrice()).thenReturn(5.0);
        Mockito.when(product.getInStock()).thenReturn(15);
        Mockito.when(product.getMin()).thenReturn(10);
        Mockito.when(product.getMax()).thenReturn(25);
        Mockito.when(product.getAssociatedParts()).thenReturn(FXCollections.observableArrayList(new InhousePart(10, "part1", 0, 5, 3, 10, 1000)));
        ObservableList<Part> lista = FXCollections.observableArrayList();
        lista.add(new InhousePart(10, "part1", 0, 5, 3, 10, 1000));
        try {
            service.addProduct("Produs 1", 5, 15, 10, 25, lista);
            Mockito.when(repository.getNumberOfProducts()).thenReturn(1);
            assert service.getNumberOfProducts() == 1;
        } catch (ValidatorException ex) {
            //System.out.println(ex.getErrorMessage());
            assert false;
        }
    }

    @Test
    public void addProductNonValid(){
        Mockito.doNothing().when(repository).addProduct(product);
        ObservableList<Part> lista = FXCollections.observableArrayList();
        lista.add(new InhousePart(10, "part1", 0, 5, 3, 10, 1000));
        lista.add(new InhousePart(10, "part2", 0, 5, 3, 10, 1000));

        try {
            service.addProduct("Produs 2", 0, 15, 10, 25, lista);
            assert false;
        } catch (ValidatorException ex) {
            assertEquals(ProductErrorMessage.NEGATIVE_PRICE.toString() + '\n', ex.getErrorMessage());
        }
    }
}