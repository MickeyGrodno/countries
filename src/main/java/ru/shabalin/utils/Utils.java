package ru.shabalin.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

public class Utils {
    private static final String PROPERTIES_FILE = "/application.properties";
    private static Properties PROFILE_PROPERTIES;

    public static String loadProperty(String propertyName) {
        String value = PROFILE_PROPERTIES.getProperty(propertyName);
        return value;
    }

    static {
        try {
            PROFILE_PROPERTIES = getPropertiesInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties getPropertiesInstance() throws IOException {
        Properties instance = new Properties();
        try (
                InputStream resource = Utils.class.getResourceAsStream(PROPERTIES_FILE);
                InputStreamReader inputStream = new InputStreamReader(resource, Charset.forName("UTF-8"))
        ) {
            instance.load(inputStream);
        }
        return instance;
    }

    public static List<List<String>> doGetRequestAndGetResponseData(String url) {
        Response response = RestAssured
                .given()
                .get(url)
                .then()
                .statusCode(200)
                .extract().response();
        List<List<String>> bordersList = response.getBody().jsonPath().get("borders");
        return bordersList;
    }

    public static String urlMaker(String url, String countryCode) {
        return loadProperty(url)+countryCode;
    }

    public static String urlMaker(String url, List<String> countryCodes) throws Exception {
        String finalUrl = loadProperty(url);
        if(countryCodes.size() == 1) {
            finalUrl += countryCodes.get(0);
        } else if(countryCodes.size() == 0) {
            throw new Exception("List contains no data");
        } else {
            for (int i = 0; i < countryCodes.size(); i++) {
                if(i==0) {
                    finalUrl += countryCodes.get(i);
                } else {
                    finalUrl += ","+countryCodes.get(i);
                }
            }
        }
        return finalUrl;
    }
}
