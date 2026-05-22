package apiTests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utils.ApiClient;

import java.util.List;

public class ProductApiTest {

    @Test
    public void getProductsTest() {

        Response response = ApiClient.get("/productsList");

        Assert.assertEquals(200, response.statusCode());

        List<String> productNames =
                response.jsonPath().getList("products.name");

        Assert.assertTrue(productNames.contains("Blue Top"));
    }
}