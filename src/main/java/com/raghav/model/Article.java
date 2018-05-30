package com.raghav.model;

public class Article {
    private Integer id;
    private String title;
    private String body;
    private String references;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "[ " +
                "id=" + id +
                ", title=" + title +
                ", body=" + body +
                ", references=" + references +
                ", url='" + url + '\'' +
                "]\n";
    }
}
