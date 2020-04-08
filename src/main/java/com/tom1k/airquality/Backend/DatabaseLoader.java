package com.tom1k.airquality.Backend;

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
        this.repository.save(new CityAir("Aveiro",39,21,"pm25"));
    }

    public CityAir getCityDetails(String name){
        CityAir city = this.repository.findByName(name);
        if(city!=null){
            return city;
        } else {
            fetchCityData(name);
            return this.repository.findByName(name);
        }
    }

    public void fetchCityData(String name){
        return;
    }
}
