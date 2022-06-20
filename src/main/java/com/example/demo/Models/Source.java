package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Please, field 'title' cannot be empty")
    @Length(min=4, max=256, message = "Title should be beteween 4 and 256")
    @Column(name="title")
    private String title;

    @NotNull(message = "Sub title cannot be null")
    @NotEmpty(message = "Please, field 'sub title' cannot be empty")
    @Length(min=4, max=256)
    @Column(name="subTitle")
    private String subTitle;

    @NotNull(message = "image cannot be null")
    @NotEmpty(message = "Please, field 'image' cannot be empty")
    @Length(min=10, max=2000)
    @Column(name="coverImage")
    private String coverImage;

    @NotNull(message = "author cannot be null")
    @NotEmpty(message = "Please, field 'author' cannot be empty")
    @Length(min=4, max=256)
    @Column(name="author")
    private String author;

    @OneToMany(mappedBy = "source")
    @JsonManagedReference
    private List<Quotation> quotations;

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
