package com.tom1k.airquality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final CityAirRepository repository;

    @Autowired
    public DatabaseLoader(CityAirRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.repository.save(new CityAir("Aveiro",39,21));
    }
}
