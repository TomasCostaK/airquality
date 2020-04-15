package com.tom1k.airquality;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @GetMapping("/search")
    public City get(@RequestParam(value = "name", defaultValue = "Porto")String city) {
        return cityService.get(city);
    }

    @CrossOrigin
    @GetMapping("/cities")
    public List<City> getCities() {
        return cityService.getCities();
    }

    @DeleteMapping("/search/{id}")
    public String delete(@PathVariable int id) {
        cityService.delete(id);
        return "City removed with id "+id;
    }


}
