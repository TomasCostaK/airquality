package com.tom1k.airquality.IntegrationTests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;


public class TestAPI {

    @Test
    void getCities(){
        String baseURI = "http://localhost:8080/api/cities";

        given()
                .relaxedHTTPSValidation()
                .when()
                .get(baseURI)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()",is(greaterThan(0)));
    }

    @Test
    void getCity(){
        String baseURI = "http://localhost:8080/api/cities";

        given()
                .relaxedHTTPSValidation()
                .when()
                .get(baseURI)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("[0].name",is("Aveiro"));
    }

}
