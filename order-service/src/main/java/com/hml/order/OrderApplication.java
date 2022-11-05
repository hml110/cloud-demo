package com.hml.order;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.hml.order.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }


    //创建并注入RestTemplate
    @LoadBalanced //实现负载均衡
    @Bean
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }

//    定义IRule实现可以修改负载均衡规则
    @Bean
    public IRule randomRule(){
        return new RandomRule();
    }

}


