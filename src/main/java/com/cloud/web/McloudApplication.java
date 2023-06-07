package com.cloud.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.cloud.web.*")
@MapperScan("com.cloud.web.mapper*")
//文件
public class McloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(McloudApplication.class, args);
	}

}
