package com.tom1k.airquality;

import com.tom1k.airquality.DatabaseLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private DatabaseLoader cityService;

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping(path="/search")
    public String search() throws IOException {
        return cityService.fetchCityData("porto");
    }

}


