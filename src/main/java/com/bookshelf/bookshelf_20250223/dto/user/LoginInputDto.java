package com.bookshelf.bookshelf_20250223.dto.user;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInputDto implements Serializable {

    private String mailAddress;
    private String password;

    public LoginInputDto() {
    }

    public LoginInputDto(String mailAddress, String password) {
        this.mailAddress = mailAddress;
        this.password = password;
    }

}
