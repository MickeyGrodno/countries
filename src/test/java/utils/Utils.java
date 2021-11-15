package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import steps.BaseTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

public class Utils extends BaseTest {
    private static final String PROPERTIES_FILE = "/application.properties";
    private static Properties PROPERTIES;

    public static String loadProperty(String propertyName) {
        return PROPERTIES.getProperty(propertyName);
    }

    static {
        try {
            PROPERTIES = getPropertiesInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties getPropertiesInstance() throws IOException {
        Properties instance = new Properties();
        try (InputStream resource = Utils.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (resource != null) {
                InputStreamReader inputStream = new InputStreamReader(resource, StandardCharsets.UTF_8);
                instance.load(inputStream);
            } else {
                throw new IOException("Property file not found " + PROPERTIES_FILE);
            }
        }
        return instance;
    }

    public static List<List<String>> doGetRequestAndGetResponseBordersData(String url) {
        scenario.write("URL request made " + RestAssured.baseURI + url);
        Response response = RestAssured
                .given()
                .get(url)
                .then()
                .statusCode(200)
                .extract().response();
        scenario.write("Response body received\n" + response.getBody().prettyPrint());
        return response.getBody().jsonPath().get("borders");
    }

    public static Response doGetRequestAndGetResponseBody(String url) {
        scenario.write("URL request made " + RestAssured.baseURI + url);
        Response response = RestAssured
                .given()
                .get(url)
                .then()
                .statusCode(200)
                .extract().response();
        scenario.write("Response body received\n" + response.getBody().prettyPrint());
        return response;
    }

    public static String urlMaker(String url, String countryCode) {
        return loadProperty(url) + countryCode;
    }

    public static String urlMaker(String url, List<String> countryCodes) throws Exception {
        StringBuilder finalUrl = new StringBuilder(loadProperty(url));
        if (countryCodes.size() == 1) {
            finalUrl.append(countryCodes.get(0));
        } else if (countryCodes.size() == 0) {
            throw new Exception("List contains no data");
        } else {
            for (int i = 0; i < countryCodes.size(); i++) {
                if (i == 0) {
                    finalUrl.append(countryCodes.get(i));
                } else {
                    finalUrl.append(",").append(countryCodes.get(i));
                }
            }
        }
        return finalUrl.toString();
    }
}
