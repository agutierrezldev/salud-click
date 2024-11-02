package com.agutierrezl.config_service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServiceApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		System.setProperty("BRANCH", dotenv.get("BRANCH"));

		//GitHub
		System.setProperty("GITHUB_USER", dotenv.get("GITHUB_USER"));
		System.setProperty("GITHUB_TOKEN", dotenv.get("GITHUB_TOKEN"));
		SpringApplication.run(ConfigServiceApplication.class, args);
	}

}
