package com.tom1k.airquality.Frontend;

import com.tom1k.airquality.Backend.DatabaseLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class HomeController {

    private DatabaseLoader cityService;

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/search")
    public String search() throws IOException {
        return this.cityService.fetchCityData("porto");
    }

}
