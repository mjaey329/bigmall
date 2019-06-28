package com.dingmj.bigmall.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dingmj.bigmall.db"})
@MapperScan("com.dingmj.bigmall.db.dao")
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
