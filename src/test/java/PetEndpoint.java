import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;

public class PetEndpoint {
    private final static String get_By_Id="/pet/{id}";
    private final static String get_Pet_By_Status="/pet/findByStatus?status{status}";
    //private final static String update_Existing_Pet="/pet";
    //private final static String update_Pet_With_Form_Data="/pet/{id}";
    private final static String delete_Pet="/pet/{id}";
    private final static String create_Pet="/pet";


    private RequestSpecification given() {
        return RestAssured
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON)
                .log()
                .all();
    }
    public ValidatableResponse createPet(String body) {
        return given()
                .body(body)
                .when()
                .post(create_Pet)
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    public ValidatableResponse deletePet (long petId) {
        return given()
                .when()
                .delete(delete_Pet, petId)
                .then()
                .log()
                .all()
                .body("message", is(String.valueOf(petId)))
                .statusCode(200);

    }
    public ValidatableResponse getById(long petId) {
        return given()
                .when()
                .get(get_By_Id)
                .then()
                .log()
                .all()
                .body("id", is(petId))
                .statusCode(200);
    }
    public ValidatableResponse getByStatus (String petStatus) {
        return given()
                .when()
                .param("status", petStatus)
                .log().all()
                .get(get_Pet_By_Status, petStatus)
                .then()
                .log().all()
                .body("status", everyItem(equalTo(petStatus)))
                .statusCode(200);
    }
}
