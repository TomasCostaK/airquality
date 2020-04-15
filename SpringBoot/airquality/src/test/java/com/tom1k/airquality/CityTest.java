package com.tom1k.airquality;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CityTest {

    @Test
    void testToString() {
        City city = new City();
        city.setName("Porto");
        assertThat(city.toString().equals("City{id=null, name='Porto', aqi=0, pm10=0, pm25=0, dominentpol='null'}"));
    }
}