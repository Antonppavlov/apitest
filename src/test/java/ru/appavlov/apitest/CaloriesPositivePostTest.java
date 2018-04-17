package ru.appavlov.apitest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import ru.appavlov.apitest.pojo.calories.request.CaloriesRequest;
import ru.appavlov.apitest.precondion.CaloriesPrecondition;
import ru.appavlov.apitest.utils.Send;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class CaloriesPositivePostTest extends CaloriesPrecondition {

    @Test
    public void testFirst() {
        CaloriesRequest caloriesRequest = new CaloriesRequest();
        caloriesRequest.setGender(true);
        caloriesRequest.setAge(23);
        caloriesRequest.setHeight(177);
        caloriesRequest.setWeight(55);
        caloriesRequest.setActivity(0);
        caloriesRequest.setTarget(0);

        Response response = Send.post(RestAssured.baseURI, caloriesRequest);
        response.print();
        response.then()
                .statusCode(200)
                .body("calorie", equalTo(1450))
                .body("proteins", equalTo(94))
                .body("fats", equalTo(35))
                .body("carbohydrates", equalTo(175));
    }

    @Test
    public void testMinParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("gender", false);
        params.put("age", 10);
        params.put("height", 30);
        params.put("weight", 1);
        params.put("activity", 1);
        params.put("target", 1);
        Response response = given().contentType("application/json").body(params).post();
        response.print();

        response.then()
                .statusCode(200)
                .body("calorie", equalTo(1450))
                .body("proteins", equalTo(94))
                .body("fats", equalTo(35))
                .body("carbohydrates", equalTo(175));
    }

    @Test
    public void testMaxParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("gender", false);
        params.put("age", 100);
        params.put("height", 220);
        params.put("weight", 300);
        params.put("activity", 2);
        params.put("target", 2);
        Response response = given().contentType("application/json").body(params).post();
        response.print();

        response.then()
                .statusCode(200)
                .body("calorie", equalTo(5580))
                .body("proteins", equalTo(371))
                .body("fats", equalTo(140))
                .body("carbohydrates", equalTo(687));
    }

    @Test
    public void testActivityIntensiveWorkouts() {
        Map<String, Object> params = new HashMap<>();
        params.put("gender", false);
        params.put("age", 100);
        params.put("height", 220);
        params.put("weight", 300);
        params.put("activity", 3);
        params.put("target", 2);
        Response response = given().contentType("application/json").body(params).post();
        response.print();

        response.then()
                .statusCode(200)
                .body("calorie", equalTo(5884))
                .body("proteins", equalTo(391))
                .body("fats", equalTo(148))
                .body("carbohydrates", equalTo(725));
    }

    @Test
    public void testActivityDailyWorkouts() {
        Map<String, Object> params = new HashMap<>();
        params.put("gender", false);
        params.put("age", 30);
        params.put("height", 130);
        params.put("weight", 30);
        params.put("activity", 4);
        params.put("target", 2);
        Response response = given().contentType("application/json").body(params).post();
        response.print();

        response.then()
                .statusCode(200)
                .body("calorie", equalTo(2208))
                .body("proteins", equalTo(148))
                .body("fats", equalTo(56))
                .body("carbohydrates", equalTo(275));
    }

    @Test
    public void testActivityDailyWorkoutsToDay() {
        Map<String, Object> params = new HashMap<>();
        params.put("gender", false);
        params.put("age", 40);
        params.put("height", 140);
        params.put("weight", 40);
        params.put("activity", 5);
        params.put("target", 2);
        Response response = given().contentType("application/json").body(params).post();
        response.print();

        response.then()
                .statusCode(200)
                .body("calorie", equalTo(2416))
                .body("proteins", equalTo(162))
                .body("fats", equalTo(61))
                .body("carbohydrates", equalTo(300));
    }

    @Test
    public void testActivityHardDailyWorkoutsToDay() {
        Map<String, Object> params = new HashMap<>();
        params.put("gender", false);
        params.put("age", 60);
        params.put("height", 160);
        params.put("weight", 60);
        params.put("activity", 6);
        params.put("target", 2);
        Response response = given().contentType("application/json").body(params).post();
        response.print();

        response.then()
                .statusCode(200)
                .body("calorie", equalTo(2867))
                .body("proteins", equalTo(189))
                .body("fats", equalTo(71))
                .body("carbohydrates", equalTo(350));
    }
}
