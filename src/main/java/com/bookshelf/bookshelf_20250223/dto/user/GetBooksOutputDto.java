package com.bookshelf.bookshelf_20250223.dto.user;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetBooksOutputDto implements Serializable {

    List<BookOutputDto> books;
}
