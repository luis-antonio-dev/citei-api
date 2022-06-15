package com.example.demo.Models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotEmpty(message = "Please, field 'content' cannot be empty")
    @Length(min=4, max=512)
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
