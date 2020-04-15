package com.tom1k.airquality;

import java.util.List;

public interface CityService {
    List<City> getCities();
    City get(String name);
    City save(City city) throws Exception;
    boolean delete(int id) throws Exception;
}
