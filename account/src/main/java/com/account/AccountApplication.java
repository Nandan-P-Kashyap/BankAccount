package com.account;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.persistence.EntityManager;

@EnableFeignClients(basePackages = "com.account.proxy")

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(jdbcTemplate!=null) {
			System.out.println("------------jdbcTemplate is created---------------");
		}
		if(em!=null) {
			System.out.println("------------em is created---------------");
		}
		if(ds!=null) {
			System.out.println("------------ds is created---------------");
		}
	}
}
