package com.cloud.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@MapperScan("com.cloud.web.*")
@MapperScan("com.cloud.web.mapper*")
@EnableTransactionManagement
public class McloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(McloudApplication.class, args);
	}

}
