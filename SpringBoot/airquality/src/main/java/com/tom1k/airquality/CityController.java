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
    public City save(@RequestBody City city) throws Exception{
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
    public boolean delete(@PathVariable int id) throws Exception{
        return cityService.delete(id);
    }


}
