import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GetByID {
    String updName = "Sharikas";
    String status = "sold";
    long addedPetId;

    @Before
    public void before2() {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        spec.setBaseUri("https://petstore.swagger.io/v2");
        spec.addHeader("Content-Type", "application/json");
        RestAssured.requestSpecification = spec.build();
    }
    @Before
    public void before1() {
        String body = "{\n" +
                "  \"id\":\"0\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Alik\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        ValidatableResponse response = given()
                .body(body)
                .when()
                .post("/pet")
                .then()
                .log()
                .all();
        addedPetId = response.extract().path("id");
        System.out.println(addedPetId);
    }
    @After
    public void deleteCreatedPreconditions () {
        given()
                .log()
                .all()
                .header("api_key", "special-key")
                .when()
                .delete("/pet/{addedPetId}", addedPetId)
                .then()
                .log()
                .all()
                .body("message", Matchers.is(String.valueOf(addedPetId)))
                .statusCode(200);
    }
    @Test
    public void getPetById () {
        given()
                .log()
                .all()
                .when()
                .get("/pet/{addedPetId}", addedPetId)
                .then()
                .log().all()
                .body("id", is(addedPetId))
                .statusCode(200);
    }
}