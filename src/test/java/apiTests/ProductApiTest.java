package apiTests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utils.ApiClient;

@Epic("API Tests")
@Feature("Product API")

public class ProductApiTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify users list API")

    public void getUsersTest() {

        Response response =
                ApiClient.get("/api/users?page=2");

        System.out.println(response.asPrettyString());

        Assert.assertEquals(
                200,
                response.statusCode()
        );

        Assert.assertTrue(
                response.jsonPath()
                        .getList("data")
                        .size() > 0
        );
    }
}