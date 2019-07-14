package com.couponapp.home.category;

public class CategoryDto {
    private String id;
    private String name;

    public CategoryDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategoryEntity() {
        return new Category(this.id, this.name);
    }
}
