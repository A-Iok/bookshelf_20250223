package com.bookshelf.bookshelf_20250223.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bookshelf.bookshelf_20250223.dto.user.LoginInputDto;
import com.bookshelf.bookshelf_20250223.entity.TUser;
import com.bookshelf.bookshelf_20250223.repository.TUserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UserService {

    @Autowired
    TUserRepository tUserRepository;

    public boolean login(LoginInputDto inputDto) {

        int[] idList = tUserRepository.getUserAtLogin(inputDto.getMailAddress(), inputDto.getPassword());

        if (ObjectUtils.isEmpty(idList)
                || idList.length == 0) {
            log.info("取得結果0件" + String.valueOf(idList));
            return false;
        }
        if (idList.length > 2) {
            log.error("取得結果2件以上" + String.valueOf(idList));
            return false;
        }

        log.info("成功");
        return true;
    }

    public void getEntity() {
        log.info("getEntity");

        List<TUser> returnList = tUserRepository.findAll();
        returnList.stream().forEach(a -> log.info(a.toString()));
        // log.info("件数" + tUserRepository.countAll());
    }
}
