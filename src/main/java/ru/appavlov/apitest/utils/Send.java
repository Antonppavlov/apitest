package ru.appavlov.apitest.utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Send {
    @Step("Делаю запрос del на url: {url}")
    public static synchronized Response del(String url) throws NotFoundURLForServiceException {
        return setBaseURL(url).delete();
    }


    @Step("Делаю запрос post(json) на url: {url}")
    public static synchronized Response post(String url, Object json) throws NotFoundURLForServiceException {
        return setBaseURL(url).contentType("application/json").body(json).post();
    }

    public static synchronized Response put(String url, Object json) throws NotFoundURLForServiceException {
        setBaseURL(url);
        return setBaseURL(url).contentType("application/json").body(json).put();
    }


    @Step("Делаю запрос post(json) на url: {url}")
    public static synchronized Response post(String url, String login, String password, Map<String, String> params) throws NotFoundURLForServiceException {
        return setBaseURL(url)
                .auth().basic(login, password)
                .params(params)
                .post();
    }

    public static synchronized Response post(String url) throws NotFoundURLForServiceException {
        return post(url, "");
    }

    @Step("Делаю запрос post(json) на url: {url}")
    public static synchronized Response post(String url, Object json, Header... headers) throws NotFoundURLForServiceException {
        RequestSpecification given = setBaseURL(url);

        for (Header header : headers) {
            given.header(header);
        }

        return given.contentType("application/json").body(json).post();
    }

    @Step("Делаю запрос post(json) на url: {url}")
    public static synchronized Response post(String url, Header header) throws NotFoundURLForServiceException {
        RequestSpecification given = setBaseURL(url);
        given.header(header);

        return given.post();
    }

    @Step("Делаю запрос post на url: {url}")
    public static synchronized Response post(String url, Map<String, String> params, Header... headers) throws NotFoundURLForServiceException {
        RequestSpecification given = setBaseURL(url);

        for (String key : params.keySet()) {
            given.param(key, params.get(key));
        }

        for (Header header : headers) {
            given.header(header);
        }

        return given.post();
    }

    @Step("Делаю запрос get на url: {url}")
    public static synchronized Response get(String url, String key, String value) throws NotFoundURLForServiceException {
        return setBaseURL(url).param(key, value).get();
    }

    @Step("Делаю запрос get на url: {url}")
    public static synchronized Response get(String url, Header... headers) throws NotFoundURLForServiceException {
        RequestSpecification given = setBaseURL(url);

        for (Header header : headers) {
            given.header(header);

        }
        return given.get();
    }

    @Step("Делаю запрос get на url: {url}")
    public static synchronized Response get(String url, Map<String, String> params) throws NotFoundURLForServiceException {
        RequestSpecification given = setBaseURL(url);

        for (String key : params.keySet()) {
            given.param(key, params.get(key));
        }

        return given.get();
    }


    @Step("Делаю запрос get на url: {url}")
    public static synchronized Response get(String url, Map<String, String> params, Header... headers) throws NotFoundURLForServiceException {
        RequestSpecification given = setBaseURL(url);

        for (String key : params.keySet()) {
            given.param(key, params.get(key));
        }

        for (Header header : headers) {
            given.header(header);
        }

        return given.get();
    }

    @Step("Делаю запрос get на url: {url}")
    public static synchronized Response get(String url) throws NotFoundURLForServiceException {
        return setBaseURL(url).get();
    }

    @Step("Делаю запрос get на url: {url}")
    public static synchronized Response get(String url, Cookie cookie) throws NotFoundURLForServiceException {
        return setBaseURL(url).cookie(cookie).get();
    }

    @Step("Валидация URL-а")
    private static RequestSpecification setBaseURL(String url) throws NotFoundURLForServiceException {
        if (url.startsWith("null") || !url.startsWith("http")) {
            String text = url.substring(4);
            throw new NotFoundURLForServiceException(text);
        }

        RestAssured.baseURI = url;
        return given().relaxedHTTPSValidation();
    }
}

