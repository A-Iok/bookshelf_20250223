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
@Entity(name = "TUser")
@Table(name = "t_user")
public class TUser implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "mailaddress")
    private String mailaddress;

    @Column(name = "created_at")
    private Date createdAt;

    public TUser() {
    }

    public TUser(String id, String userName, String password, String mailaddress, Date createdAt) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.mailaddress = mailaddress;
        this.createdAt = createdAt;

    }

    @Override
    public String toString() {
        return "TUser [getCreatedAt()=" + getCreatedAt() + ", getId()=" + getId() + ", getMailaddress()="
                + getMailaddress() + ", getPassword()=" + getPassword() + ", getUserName()=" + getUserName() + "]";
    }

}
