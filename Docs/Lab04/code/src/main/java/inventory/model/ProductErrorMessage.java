package inventory.model;

public enum ProductErrorMessage {
    EMPTY_NAME,
    NEGATIVE_MIN,
    NEGATIVE_PRICE,
    MIN_GT_MAX,
    MIN_GT_INSTOCK,
    INSTOCK_GT_MAX,
    EMPTY_PART_LIST,
    PRICE_LT_PART_PRICE
}
