package com.blue.bank.blue_bank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@SpringBootApplication
@EnableCaching 
public class BlueBankApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlueBankApplication.class, args);
	}
}