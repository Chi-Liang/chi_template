package com.fayao;

import com.fayao.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@EnableAsync
@SpringBootApplication
public class FayaoApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    JwtUtil jwtUtil;

    public static void main(String[] args) {
        SpringApplication.run(FayaoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FayaoApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(jwtUtil.createToken("aaaaaaaaaaa"));
    }
}
