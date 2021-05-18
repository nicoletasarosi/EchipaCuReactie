package inventory.validator;

public class ValidatorException extends Exception {
    private String errorMessage;
    public ValidatorException(String mesaj) {
        this.errorMessage = mesaj;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
