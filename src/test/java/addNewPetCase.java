import org.junit.Test;

import static io.restassured.RestAssured.given;


public class addNewPetCase {
    @Test
    public void addPetCase() {
        given()
                .baseUri("https://petstore.swagger.io")
                .contentType("application/json")
                .body("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"category\": {\n" +
                                "    \"id\": 0,\n" +
                                "    \"name\": \"string\"\n" +
                                "  },\n" +
                                "  \"name\": \"doggie\",\n" +
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
                                "}"
                        )
                .when()
                .post("/v2/pet/")
                .then()
                .log().all()
                .statusCode(200);
    }
}