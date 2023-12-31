package demo.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaaServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaaServer1Application.class, args);
	}

}
