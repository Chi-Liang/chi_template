package com.fundodo.fayaospace;

import com.fundodo.fayaospace.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@EnableAsync
@SpringBootApplication
public class FayaospaceApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    JwtUtil jwtUtil;

    public static void main(String[] args) {
        SpringApplication.run(FayaospaceApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FayaospaceApplication.class);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println(jwtUtil.createToken("aaaaa"));
    }
}
