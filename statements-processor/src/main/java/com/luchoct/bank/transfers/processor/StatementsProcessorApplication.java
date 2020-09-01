package com.luchoct.bank.transfers.processor;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StatementsProcessorApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(StatementsProcessorApplication.class).web(WebApplicationType.NONE).run(args);
	}

}