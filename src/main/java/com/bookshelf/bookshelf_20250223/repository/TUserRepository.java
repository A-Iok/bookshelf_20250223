package com.bookshelf.bookshelf_20250223.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookshelf.bookshelf_20250223.entity.TUser;

@Repository
public interface TUserRepository extends JpaRepository<TUser, String> {

    // CRUDがデフォルトで使える

    // @Query("SELECT COUNT(*) FROM public.t_user")
    // public long countAll();
}