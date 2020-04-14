package com.tom1k.airquality;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @TestConfiguration
    static class CityServiceImplTestContextConfiguration {

        @Bean
        public CityService cityService() {
            return new CityServiceImpl();
        }
    }

    @Mock(lenient = true)
    private CityDAOImpl cityDAO;

    @InjectMocks
    private CityServiceImpl cityService;

    @BeforeEach
    public void setUp(){
        City aveiro = new City();
        aveiro.setName("Aveiro");
        aveiro.setAqi(17);

        City braga = new City();
        braga.setName("Braga");
        braga.setAqi(12);

        List<City> cities = Arrays.asList(aveiro, braga);

        Mockito.when(cityDAO.get(aveiro.getName())).thenReturn(aveiro);
        Mockito.when(cityDAO.get(braga.getName())).thenReturn(braga);
        Mockito.when(cityDAO.get("fake_value")).thenReturn(null);
        Mockito.when(cityDAO.getCities()).thenReturn(cities);
    }

    @Test
    void get() {
        City found = cityService.get("Aveiro");
        assertThat(found.getName()).isEqualTo("Aveiro");
    }

    @Test
    void testGet1() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}