package com.tom1k.airquality;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CityController.class)
class CityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService service;

    @BeforeEach
    public void setUp() throws Exception {
    }


    @Test
    public void whenSearchNewCity_thenCreateNewCity() throws Exception{
        City city = new City();
        city.setName("Teste34");
        city.setAqi(34);
        mvc.perform(post("/api/search").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(city))).andExpect(status().isOk()).andExpect(jsonPath("$.aqi",is(34)));
    }

    @Test
    public void perform_getSearch() throws Exception {
        City city = new City();
        city.setName("TesteAll");
        city.setAqi(12);

        given(service.get("TesteAll")).willReturn(city);
        mvc.perform(get("/api/search?name=TesteAll")).andExpect(status().isOk()).andExpect(jsonPath("$.aqi",is(12)));
    }

    @Test
    public void delete_City() throws Exception {
        City city = new City();
        city.setName("TesteAll");
        city.setId(100);

        given(service.get("TesteAll")).willReturn(city);
        mvc.perform(delete("/api/search/{id}",100).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void insert_thenCheckAll() throws Exception {
        City city = new City();
        city.setName("TesteAll");


        List<City> cities = Arrays.asList(city);

        given(service.getCities()).willReturn(cities);

        mvc.perform(get("/api/cities")).andExpect(status().isOk()).andExpect(jsonPath("$[0].name",is("TesteAll")));
        reset(service);
    }

    @Test
    public void perform_postSearch() throws Exception {
        City city = new City();
        city.setName("CidadeTeste");
        mvc.perform(post("/api/search").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(city))).andExpect(status().isOk()).andExpect(jsonPath("$.name",is("CidadeTeste")));;
    }

}