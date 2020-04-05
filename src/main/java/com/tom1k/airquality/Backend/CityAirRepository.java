package com.tom1k.airquality;

import org.springframework.data.repository.CrudRepository;

public interface CityAirRepository extends CrudRepository<CityAir,Long> {
    CityAir findByName(String name);
}
