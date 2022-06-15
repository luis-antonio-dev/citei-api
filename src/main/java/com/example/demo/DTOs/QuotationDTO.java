package com.example.demo.DTOs;

public class QuotationDTO {
    private String content;
    private long id_source;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId_source() {
        return id_source;
    }

    public void setId_source(long id_source) {
        this.id_source = id_source;
    }
}
