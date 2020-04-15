package com.tom1k.airquality;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@DataJpaTest
class CityDAOImplTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityDAO cityDAO;

    @Test
    void whenFind_returnCity(){

    }
}