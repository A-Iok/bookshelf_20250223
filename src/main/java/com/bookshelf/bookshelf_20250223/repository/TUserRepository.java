package com.bookshelf.bookshelf_20250223.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookshelf.bookshelf_20250223.entity.TUser;

@Repository
public interface TUserRepository extends JpaRepository<TUser, String> {

    // CRUDがデフォルトで使える

    @Query(value = "SELECT id FROM public.t_user where mailAddress = :mailAddress and password = :password", nativeQuery = true)
    public int[] getUserAtLogin(@Param("mailAddress") String mailAddress, @Param("password") String password);

    @Query(value = "SELECT id,user_name,mailaddress FROM public.t_user where id = :id and delete_flag=false", nativeQuery = true)
    public List<TUser> getUser(@Param("id") int id);
}