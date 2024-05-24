package com.ubuntucontinues.ubuntu;

import com.ubuntucontinues.ubuntu.services.PasswordGeneratorServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintStream;

@SpringBootApplication
public class UbuntuApplication {
    public static void main(String[] args) {
        SpringApplication.run(UbuntuApplication.class, args);

    }

}
