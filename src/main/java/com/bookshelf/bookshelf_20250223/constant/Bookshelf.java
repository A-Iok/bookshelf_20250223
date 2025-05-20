package com.bookshelf.bookshelf_20250223.constant;

/**
 * 本棚種別
 */
public enum Bookshelf {
    WISH("ほしい本棚", 1),
    COLLECTION("ほしい本棚", 2),
    READING("ほしい本棚", 3);

    private String label;
    private int id;

    private Bookshelf(String label, int id) {
        this.label = label;
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public int getId() {
        return id;
    }

}
