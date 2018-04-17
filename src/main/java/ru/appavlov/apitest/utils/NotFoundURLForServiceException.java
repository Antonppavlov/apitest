package ru.appavlov.apitest.utils;

public class NotFoundURLForServiceException extends RuntimeException {

    public NotFoundURLForServiceException(String message) {
        super("\n" +
                "Базовый URL на сервис: null\n" +
                "path: " + message + "\n" +
                "gateway: " + System.getProperty("gateway") + "\n" +
                "protocol: " + System.getProperty("protocol"));
    }

}
