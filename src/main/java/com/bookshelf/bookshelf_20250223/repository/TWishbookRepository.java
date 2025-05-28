package com.bookshelf.bookshelf_20250223.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookshelf.bookshelf_20250223.entity.TBook;
import com.bookshelf.bookshelf_20250223.entity.TUser;

@Repository
public interface TWishbookRepository extends JpaRepository<TUser, String> {

    // CRUDがデフォルトで使える

    @Query(value = "SELECT id,name,author_name,publisher_name,tWishbook.created_at FROM public.t_book tBook inner join t_wishbook tWishbook on tBook.id = tWishbook.book_id where tWishbook.user_id = :userId and tWishbook.delete_flag=false and tBook.delete_flag=false order by tWishbook.created_at desc limit 3", nativeQuery = true)
    public List<TBook> getBooks(@Param("userId") int userId);
}