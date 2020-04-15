package com.tom1k.airquality;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

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
        mvc.perform(get("/api/search?name=Paris")).andExpect(status().isOk());
    }

    @Test
    public void perform_postSearch() throws Exception {
        City city = new City();
        city.setName("CidadeTeste");
        mvc.perform(post("/api/search").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(city))).andExpect(status().isOk()).andExpect(jsonPath("$.name",is("CidadeTeste")));;
    }

}