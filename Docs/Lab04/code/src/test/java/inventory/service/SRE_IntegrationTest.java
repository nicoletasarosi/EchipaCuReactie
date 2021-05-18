package inventory.service;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.ProductErrorMessage;
import inventory.repository.InventoryRepository;
import inventory.validator.ValidatorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SRE_IntegrationTest {


    private InventoryService service;

    @BeforeEach
    void setUp() {
        InventoryRepository repo = new InventoryRepository();
        this.service = new InventoryService(repo);
    }

    @Test
    public void addProductValid(){
        assertEquals(0, service.getAllProducts().size());

        ObservableList<Part> partList = FXCollections.observableArrayList();
        partList.add(new InhousePart(10, "part", 10, 5, 2, 10, 1000));
        partList.add(new InhousePart(20, "part", 20, 10, 5, 20, 1000));
        try {
            service.addProduct("Product", 100, 50, 10, 70, partList);
        } catch (ValidatorException ex) {
            assert false;
        }
        assertEquals(1, service.getAllProducts().size());
        assertEquals("Product", service.getAllProducts().get(0).getName());
        service.deleteProduct(service.lookupProduct("Product"));

        assertEquals(0, service.getAllProducts().size());
    }

    @Test
    public void addProductNonValid(){

        assertEquals(0, service.getAllProducts().size());

        ObservableList<Part> partList = FXCollections.observableArrayList();
        try {

            service.addProduct("Product", 100, 50, 10, 70, partList);
            assert false;

        } catch (ValidatorException ex) {
            assertEquals(ProductErrorMessage.EMPTY_PART_LIST.toString() + '\n', ex.getErrorMessage());
        }
        assertEquals(0, service.getAllProducts().size());
    }
}
