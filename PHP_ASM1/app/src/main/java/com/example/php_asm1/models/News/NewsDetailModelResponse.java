package com.example.php_asm1.models.News;

public class NewsDetailModelResponse {
    private int id;
    private String title, content, created_at, image, name, NAME, avatar;

    public NewsDetailModelResponse(int id, String title, String content, String created_at, String image, String name, String NAME, String avatar) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.image = image;
        this.name = name;
        this.NAME = NAME;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
