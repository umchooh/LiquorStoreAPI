package ca.vanier.LiquorStoreAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ca.vanier"})
public class LiquorStoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiquorStoreApiApplication.class, args);
	}

}
