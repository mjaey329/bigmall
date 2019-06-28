package com.dingmj.bigmall.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.dingmj.bigmall.db","com.dingmj.bigmall.admin"})
@MapperScan("com.dingmj.bigmall.db.dao")
@EnableTransactionManagement
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
