package constants;

public enum Category {
    FOODSTUFFS(5),
    BOOKS(5),
    CLOTHES(20),
    OTHER_ITEMS(3),
    CLEARANCE(20);

    private final int discount_percent;

    public int getDiscount_percent() {
        return discount_percent;
    }

    Category(int discount_percent) {
        this.discount_percent = discount_percent;
    }

}