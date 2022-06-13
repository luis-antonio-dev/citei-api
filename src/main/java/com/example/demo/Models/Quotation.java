package com.example.demo.Models;

import javax.persistence.*;

@Entity
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String content;

    @ManyToOne
    @JoinColumn(name="id_source")
    private Source sourceModel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Source getSource() {
        return sourceModel;
    }

    public void setSource(Source sourceModel) {
        this.sourceModel = sourceModel;
    }
}
