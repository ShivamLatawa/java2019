package com.example.demo.persistence;

import com.example.demo.model.Employeee;
import com.example.demo.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDB {

    private final static Logger logger = LoggerFactory.getLogger( LoadDB.class );

    @Bean
    CommandLineRunner initDatabase( EmployeeRepo employeeRepo ) {
        return args -> {
            logger.info( "loading data..." );

            employeeRepo.save( new Employeee( "Shivam", "UI Developer" ) );
            employeeRepo.save( new Employeee( "Archit", "Neurosurgeon" ) );

            logger.info( "data loaded successfully!" );
        };
    }

}
