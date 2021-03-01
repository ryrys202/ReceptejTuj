package io.github.ryrys202.receptejtuj.activities;

public class RecipeModel {
    private String name;
    private String url;
    private String description;
    private String imageUrl;

    public RecipeModel(String name, String url, String description, String imageUrl) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
