package sookook.daybyday.entity;


public enum Category {

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