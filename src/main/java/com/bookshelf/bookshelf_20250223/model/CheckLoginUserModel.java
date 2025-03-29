package com.bookshelf.bookshelf_20250223.model;

/**
 * ログイン状態をチェックする
 */
public class CheckLoginUserModel {

    private String mailAddress;

    private String password;

    private CheckLoginUserModel(String mailAddress, String password) {
        this.mailAddress = mailAddress;
        this.password = password;
    }

    public CheckLoginUserModel getCheckLoginUserModel(final String mailAddress, final String password) {

        // データ取得
        return new CheckLoginUserModel(mailAddress, password);
    }
}
