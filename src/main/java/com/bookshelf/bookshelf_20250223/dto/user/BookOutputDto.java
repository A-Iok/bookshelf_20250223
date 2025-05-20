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
public class BookOutputDto implements Serializable {

    String id;

    String name;

    String authorName;

    String publisherName;

}
