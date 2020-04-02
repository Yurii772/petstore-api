import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetPetCase {

    @Test
    public void getPetByID() {
        int id = 5;
        given()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .get("/pet/{id}", id)
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    public void updatePetsCase() {
        int id = 1;
        given()
                .baseUri("https://petstore.swagger.io/v2")
                .param("name", "sharikas")
                .param("param2", "dead")
                .when()
                .post("/pet/{id}", id)
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    public void addPetCase() {
        given()
                .baseUri("https://petstore.swagger.io/v2")
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
                .post("/pet/")
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    public void updateExPet(){
        given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType("application/json")
                .body("{\n" +
                        "  \"id\": 0,\n" +
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
                        "}")
                .when()
                .put("/pet")
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    public void delPet(){
        int idd=5;
        given()
                .log()
                .all()
                .baseUri("https://petstore.swagger.io/v2")
                .header("api_key", "special-key")
                .when()
                .delete("/pet/{idd}", idd)
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    public void getByStatus (){
        given()
                .pathParam("status", "pending")
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .get ("/pet/findByStatus?status{status}")
                .then()
                .log().all()
                .statusCode(200);
    }
}


