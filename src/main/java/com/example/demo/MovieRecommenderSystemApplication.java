package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class MovieRecommenderSystemApplication {
    public static void main(String[] args) {

//        SpringApplication.run(MovieRecommenderSystemApplication.class, args);
//        //passing name of the filter as constructor argument
//        //RecommenderImplementation recommender = new RecommenderImplementation(new ContentBasedFilter());
//
//        //call method to get recommendations
//        String[] result = recommender.recommendMovies("Finding Dory");
//
//        //display results
//        System.out.println(Arrays.toString(result));

        ApplicationContext ctx = SpringApplication.run(MovieRecommenderSystemApplication.class, args);
//        RecommenderImplementation recommender = ctx.getBean("recommenderImplementation",RecommenderImplementation.class);
//        String[] result = recommender.recommendMovies("Finding Dory");
//
//        //display results
//        System.out.println(Arrays.toString(result));
//
//        String[] beanNames = ctx.getBeanDefinitionNames();
//
//        Arrays.sort(beanNames);
//
//        for (String beanName : beanNames)
//        {
//            System.out.println(beanName);
//        }
    }
}
