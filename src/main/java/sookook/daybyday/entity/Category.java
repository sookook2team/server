package sookook.daybyday.entity;


public enum Category {
    // 여행, 맛집, 일상, 음악,

    TRAVEL("TRAVEL"),
    FODD("FOOD"),
    DAILY("DAILY"),
    MUSIC("MUSIC");

    private String categoryCode;

    Category(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCode()
    {
        return categoryCode;
    }
}
