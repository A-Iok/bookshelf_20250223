package com.bookshelf.bookshelf_20250223.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bookshelf.bookshelf_20250223.constant.Bookshelf;
import com.bookshelf.bookshelf_20250223.dto.user.BookOutputDto;
import com.bookshelf.bookshelf_20250223.dto.user.GetBooksOutputDto;
import com.bookshelf.bookshelf_20250223.dto.user.GetUserOutputDto;
import com.bookshelf.bookshelf_20250223.dto.user.LoginInputDto;
import com.bookshelf.bookshelf_20250223.dto.user.LoginOutputDto;
import com.bookshelf.bookshelf_20250223.entity.TBook;
import com.bookshelf.bookshelf_20250223.entity.TUser;
import com.bookshelf.bookshelf_20250223.repository.TUserRepository;
import com.bookshelf.bookshelf_20250223.repository.TWishbookRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UserService {

    @Autowired
    TUserRepository tUserRepository;

    @Autowired
    TWishbookRepository tWishbookRepository;

    /**
     * ログイン メイン処理
     * 
     * @param inputDto
     * @return
     */
    public LoginOutputDto login(LoginInputDto inputDto) {

        int[] idList = tUserRepository.getUserAtLogin(inputDto.getMailAddress(), inputDto.getPassword());

        LoginOutputDto outputDto = new LoginOutputDto();
        if (ObjectUtils.isEmpty(idList)
                || idList.length == 0) {
            log.info("取得結果0件" + String.valueOf(idList));
            outputDto.setResultFlg(false);
            return outputDto;
        }
        if (idList.length > 2) {
            log.error("取得結果2件以上" + String.valueOf(idList));
            outputDto.setResultFlg(false);
            return outputDto;
        }

        log.info("成功");
        outputDto.setId(String.valueOf(idList[0]));
        outputDto.setResultFlg(true);
        return outputDto;
    }

    /**
     * ユーザー取得 メイン処理
     * 
     * @param inputDto
     * @return
     */
    public GetUserOutputDto getUser(String id) {
        List<TUser> tUserList = tUserRepository.getUser(Integer.valueOf(id));
        TUser tUser = tUserList.get(0);

        GetUserOutputDto returnDto = new GetUserOutputDto(tUser.getId(), tUser.getUserName(), tUser.getMailaddress());

        return returnDto;

    }

    /**
     * 本棚取得 メイン処理
     * 
     * @param inputDto
     * @return
     */
    public GetBooksOutputDto getBooks(int userId, int bookShelfId) {

        List<TBook> booklist = new ArrayList<>();

        if (Bookshelf.WISH.getId() == bookShelfId) {
            booklist = tWishbookRepository.getBooks(userId);

        } else if (Bookshelf.COLLECTION.getId() == bookShelfId) {

        } else if (Bookshelf.READING.getId() == bookShelfId) {

        } else {
            System.out.println("本棚IDなし");
            throw new RuntimeException("本棚が未指定です。ID=" + bookShelfId);
        }

        List<BookOutputDto> books = booklist
                .stream()
                .map(e -> new BookOutputDto(e.getId(), e.getUserName(), e.getAuthorName(), e.getPubulisherName()))
                .toList();

        GetBooksOutputDto returnDto = new GetBooksOutputDto(books);

        return returnDto;

    }

    public List<TUser> getEntity() {
        log.info("getEntity");

        List<TUser> returnList = tUserRepository.findAll();
        returnList.stream().forEach(a -> log.info(a.toString()));
        return returnList;
    }
}
