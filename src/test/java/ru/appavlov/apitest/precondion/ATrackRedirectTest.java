//package ru.appavlov.apitest.precondion;
//
//import io.restassured.RestAssured;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//
//public class TrackRedirectTest extends Precondition {
//    @Test
//    public void testName() throws IOException {
//        String url = RestAssured.baseURI + "redirect/11";
////
////        Response response = given()
////                .relaxedHTTPSValidation()
////                .get(url);
////        System.out.println(response.body().print());
//
//
//        Connection.Response response = Jsoup.connect(url).followRedirects(false).execute();
//        System.out.println(response.statusCode() + " : " + response.url());
//
//        //check if URL is redirect?
//        System.out.println("Is URL going to redirect : " + response.hasHeader("location"));
//        System.out.println("Target : " + response.header("location"));
//    }
//}
