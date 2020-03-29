import org.junit.Test;

import static io.restassured.RestAssured.given;

public class updatePet {

    @Test
    public void updatePets() {
        int id = 1;
        given()
                .baseUri("https://petstore.swagger.io")
                .param("name", "sharikas")
                .param("param2", "dead")
                .when()
                .post("/v2/pet/{id}", id)
                .then()
                .log().all()
                .statusCode(200);
    }
}
