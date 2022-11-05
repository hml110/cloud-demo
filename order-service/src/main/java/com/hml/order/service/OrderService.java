package com.hml.order.service;

import com.hml.order.clients.UserClient;
import com.hml.order.mapper.OrderMapper;
import com.hml.order.pojo.Order;
import com.hml.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.用Feign来进行远程调用
        User user = userClient.findById(order.getUserId());
        //3.封装User到Order
        order.setUser(user);
        // 4.返回
        return order;
    }





/*
   //使用RestTemplate实现的服务间接口调用

   @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.利用RestTemplate发起Http请求
        //拼接url路径    使用userService的服务名代替
        String url = "http://userserver/user/"+order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        //3.封装User到Order
        order.setUser(user);
        // 4.返回
        return order;
    }*/


}
