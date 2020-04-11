package com.tom1k.airquality;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/search")
    public City save(@RequestBody City city) {
        cityService.save(city);
        return city;
    }

    @GetMapping("/search/{id}")
    public City get(@PathVariable int id) {
        return cityService.get(id);
    }

    @GetMapping("/search/{name}")
    public City get(@PathVariable String name) {
        return cityService.get(name);
    }

    @GetMapping("/cities")
    public List<City> get() {
        return cityService.get();
    }

    @DeleteMapping("/search/{id}")
    public String delete(@PathVariable int id) {
        cityService.delete(id);
        return "City removed with id "+id;
    }

    @PutMapping("/search")
    public City update(@RequestBody City employee) {
        cityService.save(employee);
        return employee;
    }

}
