import org.junit.Test;

import static io.restassured.RestAssured.given;

public class deletePet {
    @Test
    public void delPet(){
        int idd=31978;
                given()
                .baseUri("https://petstore.swagger.io")
                .header("api_key", "special-key")
                .when()
                .delete("/v2/pet/{idd}", idd)
                .then()
                .log().all()
                .statusCode(200);
    }
}
