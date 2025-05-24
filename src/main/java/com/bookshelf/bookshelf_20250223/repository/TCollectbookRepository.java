package com.bookshelf.bookshelf_20250223.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookshelf.bookshelf_20250223.entity.TBook;
import com.bookshelf.bookshelf_20250223.entity.TUser;

@Repository
public interface TCollectbookRepository extends JpaRepository<TUser, String> {

    // CRUDがデフォルトで使える

    @Query(value = "SELECT id,name,author_name,publisher_name FROM public.t_book tBook inner join t_collectbook tCollectbook on tBook.id = tCollectbook.book_id where tCollectbook.user_id = :userId and tCollectbook.delete_flag=false and tBook.delete_flag=false order by tCollectbook.created_at desc limit 3", nativeQuery = true)
    public List<TBook> getBooks(@Param("userId") int userId);
}