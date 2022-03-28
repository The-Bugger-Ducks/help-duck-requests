package com.helpduck.helpduckrequests;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.helpduck.helpduckrequests.entity.Request;
import com.helpduck.helpduckrequests.repository.RequestRepository;

@SpringBootApplication
public class HelpDuckRequestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDuckRequestsApplication.class, args);
	}

	@Component
	public static class Runner implements ApplicationRunner {
		@Autowired
		public RequestRepository repository;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			Calendar calendar = Calendar.getInstance();

			Request request = new Request();
			request.setTitle("Monitor deu tela azul");
			request.setDescription("Estava programando em Java e do nada o computador parou e deu tela azul");
			request.setRegistrationDate(calendar.getTime());

			repository.save(request);
		}
	}
}
