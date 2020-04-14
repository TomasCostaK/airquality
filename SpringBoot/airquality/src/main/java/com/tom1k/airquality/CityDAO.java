package com.tom1k.airquality;

import java.util.List;

public interface CityDAO {
    List<City> getCities();

    City get(String name);

    void save(City city);

    void delete(int id);
}
