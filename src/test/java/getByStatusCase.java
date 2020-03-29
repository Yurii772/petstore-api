import org.junit.Test;

import static io.restassured.RestAssured.given;

public class getByStatusCase {
    @Test
    public void getByStatus (){
        given()
                .pathParam("status", "pending")
                .baseUri("https://petstore.swagger.io")
                .when()
                .get ("v2/pet/findByStatus?status{status}")
                .then()
                .log().all()
                .statusCode(200);
    }
}
