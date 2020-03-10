import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetPetCase {

    @Test
    public void getPetByID() {
        int id = 0;
        given()
                .baseUri("https://petstore.swagger.io")
                .when()
                .get("/v2/pet/{id}", id)
                .then()
                .log().all()
                .statusCode(404);
    }
    @Test
    public void createPetz(){

    }
}

