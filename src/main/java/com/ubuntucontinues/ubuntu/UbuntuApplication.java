package com.ubuntucontinues.ubuntu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UbuntuApplication {

	public static void main(String[] args) {
		SpringApplication.run(UbuntuApplication.class, args);
	}

}
