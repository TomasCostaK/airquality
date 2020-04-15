package com.tom1k.airquality;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
class CityDAOImplTest {


    @Autowired
    private CityDAO cityDAO;

    @Test
    void get_singleCity() throws Exception{
        assertThat(cityDAO.get("Aveiro")).isNotNull();
    }

    @Test
    void get_avaliableCities() throws Exception{
        List<City> cities = cityDAO.getCities();
        assertThat(cities.size()).isGreaterThan(0);
    }

}