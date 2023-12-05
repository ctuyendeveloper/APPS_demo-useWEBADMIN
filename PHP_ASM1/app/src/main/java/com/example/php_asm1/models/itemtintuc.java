package com.example.php_asm1.models;

public class itemtintuc {
    private int imageId;
    private String topic;
    private String post;
    private String poster;
    private String timepost;

    public itemtintuc(int imageId, String topic, String post, String poster, String timepost) {
        this.imageId = imageId;
        this.topic = topic;
        this.post = post;
        this.poster = poster;
        this.timepost = timepost;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTimepost() {
        return timepost;
    }

    public void setTimepost(String timepost) {
        this.timepost = timepost;
    }
}
