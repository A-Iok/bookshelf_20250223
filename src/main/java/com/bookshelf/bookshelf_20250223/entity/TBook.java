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
    private String userName;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "pubulisher_name")
    private String pubulisherName;

    public TBook() {
    }

    public TBook(String id, String userName, String authorName, String pubulisherName) {
        this.id = id;
        this.userName = userName;
        this.authorName = authorName;
        this.pubulisherName = pubulisherName;
    }

    @Override
    public String toString() {
        return "TBook [id=" + id + ", userName=" + userName + ", authorName=" + authorName + ", pubulisherName="
                + pubulisherName + "]";
    }

}
