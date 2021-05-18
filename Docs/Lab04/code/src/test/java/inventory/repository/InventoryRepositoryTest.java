package inventory.repository;

import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InventoryRepositoryTest {
    private InventoryRepository repository;

    private Product mockProduct;

    @BeforeEach
    void setUp() {
        InventoryRepository repo= new InventoryRepository();
        this.repository = repo;
        mockProduct = mock(Product.class);
    }

    @Test
    public void addProductValid() {
        Mockito.when(mockProduct.getId()).thenReturn(1);
        Mockito.when(mockProduct.toString()).thenReturn("P,1,produs,1256.0,120,100,200,3");
        Mockito.when(mockProduct.getAssociatedParts()).thenReturn(FXCollections.observableArrayList());
        assert repository.getNumberOfProducts() == 0;
        repository.addProduct(mockProduct);
        assert repository.getNumberOfProducts() == 1;
        repository.deleteProduct(mockProduct);
    }
    @Test
    public void testSize(){
        assert repository.getNumberOfProducts() == 0;
        Mockito.when(mockProduct.getId()).thenReturn(1);
        Mockito.when(mockProduct.toString()).thenReturn("P,1,produs,1256.0,120,100,200,3");
        Mockito.when(mockProduct.getAssociatedParts()).thenReturn(FXCollections.observableArrayList());
        repository.addProduct(mockProduct);
        Mockito.when(mockProduct.getId()).thenReturn(2);
        Mockito.when(mockProduct.toString()).thenReturn("P,2,produs,1256.0,120,100,200,3");
        repository.addProduct(mockProduct);
        assert repository.getNumberOfProducts() == 2;
        repository.deleteProduct(mockProduct);
        Mockito.when(mockProduct.toString()).thenReturn("P,1,produs,1256.0,120,100,200,3");
        Mockito.when(mockProduct.getAssociatedParts()).thenReturn(FXCollections.observableArrayList());
        repository.deleteProduct(mockProduct);
    }
}
