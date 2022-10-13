package com.gridnine.testing;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {

    //TODO: do not forget to answer theoretical questions

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);

        ctx.close();
    }
}
