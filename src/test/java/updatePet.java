import org.junit.Test;

import static io.restassured.RestAssured.given;

public class updatePet {
        @Test
        public void updatePet() {
            int id = 11;
            given()
                    .baseUri("https://petstore.swagger.io")
                    .param("name", "sharikas")
                    .param("status", "dead")
                    .when()
                    .post("/v2/pet/{id}", id)
                    .then()
                    .log().all()
                    .statusCode(200);
        }
    }


