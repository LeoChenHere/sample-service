package org.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		for( String arg : args ){
			log.info("arg {}", arg); // print : java -jar xxx.jar aaa bbb ccc
		}
		SpringApplication.run(Application.class, args);
	}
}

