package com.bookshelf.bookshelf_20250223.controller;

import java.io.StringWriter;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.core.style.ToStringStyler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookshelf.bookshelf_20250223.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bookshelf.bookshelf_20250223.dto.user.GetUserInputDto;
import com.bookshelf.bookshelf_20250223.dto.user.GetUserOutputDto;
import com.bookshelf.bookshelf_20250223.dto.user.LoginInputDto;
import com.bookshelf.bookshelf_20250223.dto.user.LoginOutputDto;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * ユーザー取得
     * 
     * @param requestDto
     * @return
     */
    @GetMapping("")
    public ResponseEntity<GetUserOutputDto> get(@RequestParam String id) {
        log.info("/get start");

        GetUserOutputDto dto = userService.getUser(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * ログイン
     * 
     * @param requestDto
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginOutputDto> postLogin(@RequestBody LoginInputDto requestDto) {
        log.info("開始：/login" + requestDto.toString());

        // // バリデーションチェック
        // if (bindingResult.hasErrors()) {
        // log.info("バリデーションエラーあり");
        // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }

        // serviceを呼ぶ
        LoginOutputDto loginOutputDto = userService.login(requestDto);

        if (!loginOutputDto.isResultFlg()) {
            log.info("バリデーションエラーあり");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // response.okかngを返却
        HttpStatus status = HttpStatus.OK;

        // ObjectMapper mapper = new ObjectMapper();
        // LoginInputDto loginInputDto = new LoginInputDto();
        // loginInputDto.setMailAddress("a");
        // loginInputDto.setPassword("p");

        // String json = mapper.writeValueAsString(loginInputDto);

        return ResponseEntity.ok(loginOutputDto);
        // return new ResponseEntity<String>("{\"response\":\"200ok\"}", status);
    }

}
