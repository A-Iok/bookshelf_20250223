package com.bookshelf.bookshelf_20250223.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshelf.bookshelf_20250223.entity.TUser;
import com.bookshelf.bookshelf_20250223.repository.TUserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LoginService {

    @Autowired
    TUserRepository tUserRepository;

    public void getEntity() {
        log.info("getEntity");

        List<TUser> returnList = tUserRepository.findAll();
        returnList.stream().forEach(a -> log.info(a.toString()));
        // log.info("件数" + tUserRepository.countAll());
    }
}
