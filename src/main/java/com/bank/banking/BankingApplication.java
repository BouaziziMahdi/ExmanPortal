package com.bank.banking;

import com.bank.banking.Services.impl.AuthenticationServiceImpl;
import com.bank.banking.dto.Userdto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class
BankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

	public CommandLineRunner commandLineRunner(
			AuthenticationServiceImpl service
	) {
		return args -> {
			var admin = Userdto.builder()
					.firstname( "mahdi" )
					.lastname( "bouazizi" )
					.email( "admin@gmail.com" )
					.password( "password" )
					.build();
			System.out.println( "Admin token: " + service.register( admin ).getToken() );

		};
	}
}
