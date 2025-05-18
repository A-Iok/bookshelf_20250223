package com.bookshelf.bookshelf_20250223.dto.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginOutputDto implements Serializable {

    private boolean resultFlg;

    private String id;

}
