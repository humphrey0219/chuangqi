package com.chuangqi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application要放在所有类的根，不能放到包里面，否则就无法扫描
 * @author wmk
 *
 */
@SpringBootApplication //自动加载配置信息
@MapperScan("com.chuangqi.dao")
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
