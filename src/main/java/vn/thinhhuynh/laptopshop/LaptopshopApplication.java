package vn.thinhhuynh.laptopshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

// @SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
// dung cai nay de off security de chay du an code cho de

@SpringBootApplication
public class LaptopshopApplication {

	public static void main(String[] args) {

		// container
		ConfigurableApplicationContext thinhhuynh = SpringApplication.run(LaptopshopApplication.class, args);
		for (String s : thinhhuynh.getBeanDefinitionNames()) {
			System.out.println(s);
		}
	}

}
