package com.hml.order.clients;

import com.hml.order.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hml
 * @version 1.0
 * @description:
 * @date 2022/11/5 20:07
 */
@FeignClient("userserver")
public interface UserClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);
}
