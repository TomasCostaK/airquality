package com.tom1k.airquality;

import java.util.List;

public interface CityService {
    List<City> get();
    City get(String name);
    void save(City city);
    void delete(int id);
}
