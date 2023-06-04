package com.example.batchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchServiceApplication {

	public static void main( String[] args ) {

		System.exit( SpringApplication.exit( SpringApplication.run( BatchServiceApplication.class, args ) ) );
	}
}
