package com.bookshelf.bookshelf_20250223.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "TBook")
@Table(name = "t_book")
public class TBook implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "publisher_name")
    private String publisherName;

    @Column(name = "created_at")
    private String createdAt;

    public TBook() {
    }

    public TBook(String id, String name, String authorName, String publisherName, String createdAt) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TBook [id=" + id + ", name=" + name + ", authorName=" + authorName + ", publisherName="
                + publisherName + ", createdAt="
                + createdAt + "]";
    }

}
