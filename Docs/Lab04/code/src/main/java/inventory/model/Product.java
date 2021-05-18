
package inventory.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Product extends Item {
    
    // Declare fields
    private ObservableList<Part> associatedParts;

    // Constructor
    public Product(int productId, String name, double price, int inStock, int min, int max, ObservableList<Part> partList) {
        super(productId, name, price, inStock, min, max);
        this.associatedParts = partList;
    }
    
    // Getters
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    // Setters
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }
    
    // Other methods
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    
    public void removeAssociatedPart(Part part) {
        associatedParts.remove(part);
    }
    
    public Item lookupAssociatedPart(String searchPart) {
        for(Part p:associatedParts) {
            if(p.getName().contains(searchPart) || Integer.toString(p.getId()).equals(searchPart)) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Generate an error message for invalid values in a product
     * and evaluate whether the sum of the price of associated parts
     * is less than the price of the resulting product.
     * A valid product will return an empty error message string.
     * @param name
     * @param min
     * @param max
     * @param inStock
     * @param price
     * @param parts
     * @return 
     */
    public static List<ProductErrorMessage> isValidProduct(String name, double price, int inStock, int min, int max, ObservableList<Part> parts) {
        double priceOfParts = 0.00;
        for (Part part : parts) {
            priceOfParts += part.getPrice();
        }
        List<ProductErrorMessage> messageCodes = new ArrayList<>();
        int index = 0;
        if (name.equals("")) {
            messageCodes.add(ProductErrorMessage.EMPTY_NAME);
        }
        if (min < 0) {
            messageCodes.add(ProductErrorMessage.NEGATIVE_MIN);
        }
        if (price < 0.01) {
            messageCodes.add(ProductErrorMessage.NEGATIVE_PRICE);
        }
        if (min > max) {
            messageCodes.add(ProductErrorMessage.MIN_GT_MAX);
        }
        if(inStock < min) {
            messageCodes.add(ProductErrorMessage.MIN_GT_INSTOCK);
        }
        if(inStock > max) {
            messageCodes.add(ProductErrorMessage.INSTOCK_GT_MAX);
        }
        if (parts.isEmpty()) {
            messageCodes.add(ProductErrorMessage.EMPTY_PART_LIST);
        }
        if (priceOfParts > price) {
            messageCodes.add(ProductErrorMessage.PRICE_LT_PART_PRICE);
        }
        return messageCodes;
    }

    @Override
    public String toString() {
        return "P," + this.getId() + "," + this.getName() + "," + this.getPrice() + "," +
                this.getInStock() + "," + this.getMin() + "," + this.getMax();
    }
}
