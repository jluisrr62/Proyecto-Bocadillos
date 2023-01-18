package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.example.demo.persistencia.TablasBD;



@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
//		TablasBD t = new TablasBD();
//		t.crearTablas();
		
		SpringApplication.run(SpringJpaApplication.class, args);
	}

}
