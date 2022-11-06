package com.hml.user.web;

import com.hml.user.config.PatternProperties;
import com.hml.user.pojo.User;
import com.hml.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope   //配置热更新
public class UserController {

    @Autowired
    private UserService userService;


//    @Value("${pattern.dateformet}")
//    private String dateformat;

    //注入类
    @Autowired
    private PatternProperties patternProperties;

    @GetMapping("/now")
    public String getDateformat(){
        //利用LocalDateTime.now()方法获取当前时间，同时传入一个指定格式化的格式
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformet()));
        return format;
    }

    @GetMapping("prop")
    public PatternProperties getPatternProperties(){
        //这个类会被解析成json回显给页面
       return patternProperties;
    }


    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id,@RequestHeader(value = "Truth",required = false) String truth) {
        System.out.println("truth = " + truth);
        return userService.queryById(id);
    }
}
