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
}
    public class updatePet {

    @Test
    public void updatePets() {
        int id = 1;
        given()
                .baseUri("https://petstore.swagger.io")
                .when()
                .post("/v2/pet/{id}", id)
                .then()
                .log().all()
                .statusCode(200);
    }
}

