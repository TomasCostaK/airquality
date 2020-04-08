package com.tom1k.airquality.Backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CityAirRepository extends JpaRepository<CityAir,Long> {
    CityAir findByName(String name);
}
