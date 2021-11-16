package ru.shabalin.utils;

import io.restassured.response.Response;

import java.util.List;

public class StepData {
    private Response response;
    private List<List<String>> responseList;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<List<String>> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<List<String>> responseList) {
        this.responseList = responseList;
    }
}
