package inventory.validator;

import inventory.model.Product;

public class ValidatorProduct {
    public void validate(Product product) throws ValidatorException {
        String errorMessage = "";
        if(product.getPrice() == 0.0) {
            errorMessage = errorMessage + "Pretul nu poate sa fie zero\n";
        }
        if(product.getPrice() < 0.0) {
            errorMessage = errorMessage + "Pretul nu poate sa fie negativ\n";
        }
        if(product.getInStock() > product.getMax()) {
            errorMessage = errorMessage + "Stocul nu poate sa fie mai mare decat stocul maxim\n";
        }
        if(product.getInStock() < product.getMin()) {
            errorMessage = errorMessage + "Stocul nu poate sa fie mai mic decat stocul minim\n";
        }
        if(!errorMessage.equals("")) {
            throw new ValidatorException(errorMessage);
        }
    }
}
