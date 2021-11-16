package ru.shabalin.utils;

import io.restassured.response.Response;
import lombok.Data;

import java.util.List;

@Data
public class StepData {
    private Response response;
    private List<List<String>> responseList;
}
