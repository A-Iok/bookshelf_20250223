package com.bookshelf.bookshelf_20250223.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookshelf.bookshelf_20250223.entity.TBook;
import com.bookshelf.bookshelf_20250223.entity.TUser;

@Repository
public interface TReadbookRepository extends JpaRepository<TUser, String> {

    // CRUDがデフォルトで使える

    @Query(value = "SELECT id,name,author_name,publisher_name FROM public.t_book tBook inner join t_readbook tReadbook on tBook.id = tReadbook.book_id where tReadbook.user_id = :userId and tReadbook.delete_flag=false and tBook.delete_flag=false order by tReadbook.created_at desc limit 3", nativeQuery = true)
    public List<TBook> getBooks(@Param("userId") int userId);
}