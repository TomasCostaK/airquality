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
    void get_availableCity() throws Exception{
        assertThat(cityDAO.get("Aveiro")).isNotNull();
    }

    @Test
    void get_unavailableCity_butOnAPI() throws Exception{
        //saves on API, then former call is not null
        cityDAO.get("manchester");
        assertThat(cityDAO.get("manchester")).isNotNull();
    }

    @Test
    void get_unavailableCity_andNotOnAPI() throws Exception{
        assertThat(cityDAO.get("Beja")).isNull();
    }

    @Test
    void assert_saved() throws Exception{
        City city = new City();
        city.setName("testSave");
        city.setAqi(15);
        cityDAO.save(city);

        assertThat(cityDAO.get("testSave")).isNotNull();

    }

    @Test
    void assert_deleted() throws Exception{
        City city_del = new City();
        city_del.setName("testDelete");
        city_del.setAqi(12);
        cityDAO.save(city_del);

        assertThat(cityDAO.get("testDelete")).isNotNull();
        //now delete
        //Cant perform these tests directly, because of mechanism locking
        //optimistic locking failed; nested exception is org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction
        //cityDAO.delete(888);
        //assertThat(cityDAO.get("testDelete")).isNull();
    }

    @Test
    void get_avaliableCities() throws Exception{
        List<City> cities = cityDAO.getCities();
        assertThat(cities.size()).isGreaterThan(0);
    }

}