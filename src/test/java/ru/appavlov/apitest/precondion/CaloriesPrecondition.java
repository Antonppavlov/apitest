package ru.appavlov.apitest.precondion;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class CaloriesPrecondition {
    @BeforeClass
    public void beforeClass() {
        RestAssured.filters(
                new AllureRestAssured()
        );
        String baseUrl = System.getProperty("url");
        RestAssured.baseURI = baseUrl + "calories/calculation";
    }
}
