package com.tez.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
	
	@Bean
    public SparkConf sparkConf() {
        SparkConf sparkConf = new SparkConf()
                .setAppName("tez")
                .setMaster("local");

        return sparkConf;
    }

    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkConf());
    }

    @Bean
    public SparkSession sparkSession() {
    	SparkSession ss = SparkSession.builder()
        		.sparkContext(javaSparkContext().sc()).
        		appName("tez")
        		.master("local")
        		.getOrCreate();            
    	ss.sparkContext().setLogLevel("ERROR");
    	return ss;
    }

}
