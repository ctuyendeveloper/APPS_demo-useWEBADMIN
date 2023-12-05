package com.example.php_asm1.models.News;

public class NewsModelResponse {
    private int id, seen;
    private String title, content, created_at, image, name , NAME;

    public NewsModelResponse() {
    }

    public NewsModelResponse(int id, int seen, String title, String content, String created_at, String image, String name, String NAME) {
        this.id = id;
        this.seen = seen;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.image = image;
        this.name = name;
        this.NAME = NAME;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
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
}
