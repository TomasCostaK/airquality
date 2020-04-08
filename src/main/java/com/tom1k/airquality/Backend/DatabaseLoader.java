package com.tom1k.airquality.Backend;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        fetchCityData("porto");
    }

    public CityAir getCityDetails(String name){
        CityAir city = this.repository.findByName(name);
        if(city!=null){
            return city;
        } else {
            try {
                fetchCityData(name);
            } catch (IOException e){
                System.out.println(e);
            }
            return this.repository.findByName(name);
        }
    }

    public String fetchCityData(String name) throws IOException {
        URL url = new URL("https://api.waqi.info/feed/beijing/?token=76cd24d48b0fd37719669178c449821326315463");
        URLConnection connection = url.openConnection();

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream())))
        {
            String line = in.readLine();
            System.out.println(line);
            return line;
        }
    }
}
