import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GetPetCase {
    @Before
    public void before () {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        spec.setBaseUri("https://petstore.swagger.io/v2");
        spec.addHeader("ContentType", "application/json");
        RestAssured.requestSpecification=spec.build();
    }

    @Test
    public void getPetByID() {
        int id = 5795;
        given()
                .log()
                .all()
                .when()
                .get("/pet/{id}", id)
                .then()
                .log().all()
                .body("id", is(id))
                .statusCode(200);
    }
    @Test
    public void updatePetsCase() {
        int id = 5795;
        given()
                .param("name" , "sharikas")
                .param("status", "sold")
                .log().all()
                .when()
                .post("/pet/{id}", id)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void updateExPet(){
        given()
                .body("{\n" +
                        "  \"id\": 8,\n" +
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
                        "  \"status\": \"sold\"\n" +
                        "}")
                .log().all()
                .when()
                .put("/pet")
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    public void addPetCase() {
        String body = "{\n" +
                "  \"id\": 454,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"UmaTurman\",\n" +
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
        given()
                .contentType("application/x-www-form-urlencoded")
                .body (body)
                .log().all()
                .when()
                .post("/pet/")
                .then()
                .log().all()
                .statusCode(200);
    }


    @Test
    public void delPet(){
        int id=5;
        given()
                .log()
                .all()
                .header("api_key", "special-key")
                .when()
                .delete("/pet/{id}", id)
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    public void getByStatus (){
        String status="pending";
        given()
                .when()
                .log().all()
                .get ("/pet/findByStatus?status{status}", status)
                .then()
                .log().all()
                .statusCode(200);
    }
}


