package api;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utils.ApiClient;

import java.util.HashMap;
import java.util.Map;

public class UserApiTest {

    @Test
    public void createUserTest() {

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("name", "BNMK");
        requestBody.put("job", "QA Automation Engineer");

        Response response =
                ApiClient.post("/api/users", requestBody);

        System.out.println(response.asPrettyString());

        Assert.assertEquals(201, response.statusCode());

        Assert.assertEquals(
                "BNMK",
                response.jsonPath().getString("name")
        );

        Assert.assertEquals(
                "QA Automation Engineer",
                response.jsonPath().getString("job")
        );
    }
}