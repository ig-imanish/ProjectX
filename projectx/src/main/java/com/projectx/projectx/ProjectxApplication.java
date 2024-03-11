package com.projectx.projectx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectxApplication.class, args);

		// UserService userService = context.getBean(UserService.class);

		// User user = new User();
		// user.setNickname("nickanme");
		// user.setEmail("nickname@yahoo.com");
		// user.setPassword("123");

		// userService.insertUser(user);
		// System.out.println("Done " + user);
	}

}
