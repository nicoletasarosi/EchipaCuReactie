package inventory.service;

import inventory.model.*;
import inventory.repository.InventoryRepository;
import inventory.validator.ValidatorException;
import inventory.validator.ValidatorProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class InventoryService {

    private InventoryRepository repo;

    public InventoryService(InventoryRepository repo){
        this.repo = repo;
    }

    public void addInhousePart(String name, double price, int inStock, int min, int  max, int partDynamicValue){
        InhousePart inhousePart = new InhousePart(repo.getAutoPartId(), name, price, inStock, min, max, partDynamicValue);
        repo.addPart(inhousePart);
    }

    public void addOutsourcePart(String name, double price, int inStock, int min, int  max, String partDynamicValue){
        OutsourcedPart outsourcedPart = new OutsourcedPart(repo.getAutoPartId(), name, price, inStock, min, max, partDynamicValue);
        repo.addPart(outsourcedPart);
    }

    public void addProduct(String name, double price, int inStock, int min, int  max, ObservableList<Part> addParts) throws ValidatorException {
        List<ProductErrorMessage> errorMessages = Product.isValidProduct(name, price, inStock, min, max, addParts);

        if(errorMessages.isEmpty()) {
            repo.addProduct(new Product(repo.getAutoProductId(), name, price, inStock, min, max, addParts));
        } else {
            String message = "";
            for(ProductErrorMessage productErrorMessage : errorMessages) {
                message += productErrorMessage.toString() +  '\n';
            }
            throw new ValidatorException(message);
        }
    }

    public ObservableList<Part> getAllParts() {
        return repo.getAllParts();
    }

    public ObservableList<Product> getAllProducts() {
        return repo.getAllProducts();
    }

    public Part lookupPart(String search) {
        return repo.lookupPart(search);
    }

    public Product lookupProduct(String search) {
        return repo.lookupProduct(search);
    }

    public void updateInhousePart(int partIndex, int partId, String name, double price, int inStock, int min, int max, int partDynamicValue){
        InhousePart inhousePart = new InhousePart(partId, name, price, inStock, min, max, partDynamicValue);
        repo.updatePart(partIndex, inhousePart);

        //daca se actualizeaza o piesa, trebuie actualizate toate produsele care o contin
        for(Product product : repo.getAllProducts()) {
            ObservableList<Part> list = FXCollections.observableArrayList();
            for(Part p : product.getAssociatedParts()) {
                if(p.getId() == inhousePart.getId()) {
                    list.add(inhousePart);
                } else {
                    list.add(p);
                }
            }
            product.setAssociatedParts(list);
        }
    }

    public void updateOutsourcedPart(int partIndex, int partId, String name, double price, int inStock, int min, int max, String partDynamicValue){
        OutsourcedPart outsourcedPart = new OutsourcedPart(partId, name, price, inStock, min, max, partDynamicValue);
        repo.updatePart(partIndex, outsourcedPart);

        //daca se actualizeaza o piesa, trebuie actualizate toate produsele care o contin
        for(Product product : repo.getAllProducts()) {
            ObservableList<Part> list = FXCollections.observableArrayList();
            for(Part p : product.getAssociatedParts()) {
                if(p.getId() == outsourcedPart.getId()) {
                    list.add(outsourcedPart);
                } else {
                    list.add(p);
                }
            }
            product.setAssociatedParts(list);
        }
    }

    public int getNumberOfProducts() {
        return repo.getNumberOfProducts();
    }

    public void updateProduct(int productIndex, int productId, String name, double price, int inStock, int min, int max, ObservableList<Part> addParts){
        Product product = new Product(productId, name, price, inStock, min, max, addParts);
        repo.updateProduct(productIndex, product);
    }

    public void deletePart(Part part){
        //daca se sterge o parte, trebuie sterse toate produsele care o contin
        ObservableList<Product> deletable = FXCollections.observableArrayList();

        for(Product product : repo.getAllProducts()) {
            for(Part p : product.getAssociatedParts()) {
                if(p.getId() == part.getId()) {
                    deletable.add(product);
                    break;
                }
            }
        }
        for(Product product : deletable) {
            repo.deleteProduct(product);
        }
        repo.deletePart(part);
    }

    public void deleteProduct(Product product){
        repo.deleteProduct(product);
    }

    public void deleteProductById(Product product){
        repo.deleteProductById(product);
    }

}
