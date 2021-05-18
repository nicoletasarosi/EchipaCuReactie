package inventory.repository;

import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepository;
import inventory.validator.ValidatorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WBT_InventoryRepositoryTest {
    private InventoryRepository repository;

    @BeforeEach
    void setUp() {
        InventoryRepository repo= new InventoryRepository();
        this.repository = repo;
    }

    @Test
    public void lookupProductTest1(){
        ObservableList<Part> parts = FXCollections.observableArrayList();
        Product product1 = new Product(1,"mic",10.0,10,5,15, parts);
        repository.addProduct(product1);
        Product result = repository.lookupProduct("mic");
        assert result.getId() == product1.getId();
        repository.deleteProduct(product1);
    }
    @Test
    public void lookupProductTest2(){
        ObservableList<Part> parts = FXCollections.observableArrayList();
        Product product1 = new Product(1,"amici",10.0,10,5,15, parts);
        repository.addProduct(product1);
        Product result = repository.lookupProduct("mic");
        assert result.getId() == product1.getId();
        repository.deleteProduct(product1);
    }
    @Test
    public void lookupProductTest3(){
        Product result = repository.lookupProduct("mic");
        assert result == null;
    }
    @Test
    public void lookupProductTest4(){
        ObservableList<Part> parts = FXCollections.observableArrayList();
        Product product1 = new Product(1,"prod1",10.0,10,5,15, parts);
        Product product2 = new Product(2,"prod2",10.0,10,5,15, parts);
        Product product3 = new Product(3,"prod3",10.0,10,5,15, parts);
        Product product4 = new Product(4,"mici",10.0,10,5,15, parts);
        Product product5 = new Product(5,"prod4",10.0,10,5,15, parts);
        repository.addProduct(product1);
        repository.addProduct(product2);
        repository.addProduct(product3);
        repository.addProduct(product4);
        repository.addProduct(product5);
        Product result = repository.lookupProduct("mic");
        assert result.getId() == product4.getId();
        repository.deleteProduct(product1);
        repository.deleteProduct(product2);
        repository.deleteProduct(product3);
        repository.deleteProduct(product4);
        repository.deleteProduct(product5);
    }
    @Test
    public void lookupProductTest5(){
        ObservableList<Part> parts = FXCollections.observableArrayList();
        Product product1 = new Product(1,"prod1",10.0,10,5,15, parts);
        Product product2 = new Product(2,"prod2",10.0,10,5,15, parts);
        Product product3 = new Product(3,"prod3",10.0,10,5,15, parts);
        Product product4 = new Product(4,"prod4",10.0,10,5,15, parts);
        Product product5 = new Product(5,"mici",10.0,10,5,15, parts);
        repository.addProduct(product1);
        repository.addProduct(product2);
        repository.addProduct(product3);
        repository.addProduct(product4);
        repository.addProduct(product5);
        Product result = repository.lookupProduct("mic");
        assert result.getId() == product5.getId();
        repository.deleteProduct(product1);
        repository.deleteProduct(product2);
        repository.deleteProduct(product3);
        repository.deleteProduct(product4);
        repository.deleteProduct(product5);
    }
    @Test
    public void lookupProductTest6(){
        ObservableList<Part> parts = FXCollections.observableArrayList();
        Product product1 = new Product(1,"prod1",10.0,10,5,15, parts);
        Product product2 = new Product(2,"prod2",10.0,10,5,15, parts);
        Product product3 = new Product(3,"prod3",10.0,10,5,15, parts);
        Product product4 = new Product(5,"prod4",10.0,10,5,15, parts);
        repository.addProduct(product1);
        repository.addProduct(product2);
        repository.addProduct(product3);
        repository.addProduct(product4);
        Product result = repository.lookupProduct("mic");
        assert result == null;
        repository.deleteProduct(product1);
        repository.deleteProduct(product2);
        repository.deleteProduct(product3);
        repository.deleteProduct(product4);
    }
    @Test
    public void lookupProductTest7(){
        ObservableList<Part> parts = FXCollections.observableArrayList();
        Product product1 = new Product(1,"prod1",10.0,10,5,15, parts);
        Product product2 = new Product(2,"amic",10.0,10,5,15, parts);
        Product product3 = new Product(3,"prod2",10.0,10,5,15, parts);
        Product product4 = new Product(4,"prod3",10.0,10,5,15, parts);
        Product product5 = new Product(5,"prod4",10.0,10,5,15, parts);
        repository.addProduct(product1);
        repository.addProduct(product2);
        repository.addProduct(product3);
        repository.addProduct(product4);
        repository.addProduct(product5);
        Product result = repository.lookupProduct("mic");
        assert result.getId() == product2.getId();
        repository.deleteProduct(product1);
        repository.deleteProduct(product2);
        repository.deleteProduct(product3);
        repository.deleteProduct(product4);
        repository.deleteProduct(product5);
    }
    @Test
    public void lookupProductTest8(){
        ObservableList<Part> parts = FXCollections.observableArrayList();
        Product product1 = new Product(1,"prod1",10.0,10,5,15, parts);
        Product product2 = new Product(2,"prod2",10.0,10,5,15, parts);
        Product product3 = new Product(3,"prod3",10.0,10,5,15, parts);
        Product product4 = new Product(4,"prod4",10.0,10,5,15, parts);
        Product product5 = new Product(5,"prod5",10.0,10,5,15, parts);
        Product product6 = new Product(6,"amici",10.0,10,5,15, parts);
        Product product7 = new Product(7,"prod6",10.0,10,5,15, parts);
        Product product8 = new Product(8,"prod7",10.0,10,5,15, parts);
        repository.addProduct(product1);
        repository.addProduct(product2);
        repository.addProduct(product3);
        repository.addProduct(product4);
        repository.addProduct(product5);
        repository.addProduct(product6);
        repository.addProduct(product7);
        repository.addProduct(product8);
        Product result = repository.lookupProduct("mic");
        assert result.getId() == product6.getId();
        repository.deleteProduct(product1);
        repository.deleteProduct(product2);
        repository.deleteProduct(product3);
        repository.deleteProduct(product4);
        repository.deleteProduct(product5);
        repository.deleteProduct(product6);
        repository.deleteProduct(product7);
        repository.deleteProduct(product8);
    }
}
