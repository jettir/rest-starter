package com.jettir.modeler;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.jettir.modeler.domain.Author;
import com.jettir.modeler.domain.Post;
import com.jettir.modeler.repository.AuthorRepository;
import com.jettir.modeler.repository.PostRepository;

@SpringBootApplication
@ComponentScan({"com.jettir.modeler"})
public class RestStarterApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(RestStarterApplication.class, args);
    }
   

    
}
