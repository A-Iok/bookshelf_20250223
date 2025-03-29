package com.bookshelf.bookshelf_20250223.dto.user;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginInputDto implements Serializable {

    @NotNull(message = "メールアドレスは必ず入力してください。")
    private String mailAddress;

    @NotNull(message = "パスワードは必ず入力してください。")
    private String password;

}
