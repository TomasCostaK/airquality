package com.tom1k.airquality;

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
    public void setUp() throws Exception {
        City aveiro = new City();
        aveiro.setName("Aveiro");
        aveiro.setAqi(17);

        City braga = new City();
        braga.setName("Braga");
        braga.setAqi(12);
        braga.setId(123);

        List<City> cities = Arrays.asList(aveiro, braga);

        Mockito.when(cityDAO.get(aveiro.getName())).thenReturn(aveiro);
        Mockito.when(cityDAO.get(braga.getName())).thenReturn(braga);
        Mockito.when(cityDAO.get("fake_value")).thenReturn(null);
        Mockito.when(cityDAO.getCities()).thenReturn(cities);
        Mockito.when(cityDAO.delete(123)).thenReturn(true);
    }

    @Test
    void get() {
        City found = cityService.get("Aveiro");
        assertThat(found.getName()).isEqualTo("Aveiro");
    }

    @Test
    void assert_invalidname(){
        City found = cityService.get("cidadex");
        assertThat(found).isNull();
    }

    @Test
    void obtain_afterSave() throws Exception{
        City city = new City();
        city.setId(100);
        city.setName("Teste1");
        cityService.save(city);
        assertThat(cityService.get("Teste1")).isNull();
    }

    @Test
    void assert_notdeleting_nonexistent() throws Exception{
        boolean worked = cityService.delete(69);
        assertThat(worked).isFalse();
    }

    @Test
    void assert_delete_notfoundafter() throws Exception{
        City city_before = cityService.get("Braga");
        assertThat(city_before).isNotNull();

        //declared as 100 in beforeeach
        boolean worked = cityService.delete(123);
        assertThat(worked).isTrue();

        //cityService.delete(100);

        //City city_found = cityService.get("TestUnit");
        //assertThat(city_found).isNull();
    }

    @Test
    void obtain_all() throws Exception{
        City city = new City();
        city.setName("cidade");
        city.setId(100);
        cityService.save(city);

        List<City> found = cityService.getCities();
        assertThat(found).isNotNull();
    }

}