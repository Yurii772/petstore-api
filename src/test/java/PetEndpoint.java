import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class PetEndpoint {
    private final static String get_By_Id="/pet/{id}";
    private final static String get_Pet_By_Status="/pet/findByStatus?status{status}";
    private final static String update_Existing_Pet="/pet";
    private final static String update_Pet_With_Form_Data="/pet/{id}";
    private final static String delete_Pet="/pet/{id}";
    private final static String create_Pet="/pet";

    static {
        RestAssured.filters(new RequestLoggingFilter(LogDetail.ALL));
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    private RequestSpecification given() {
        return RestAssured
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse createPet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(create_Pet)
                .then()
                .statusCode(SC_OK);
    }

    public ValidatableResponse deletePet (long petId) {
        return given()
                .header("api_key", "special-key")
                .when()
                .delete(delete_Pet, petId)
                .then()
                .body("message", is(String.valueOf(petId)))
                .statusCode(SC_OK);
    }

    public ValidatableResponse getById(long petId) {
        return given()
                .when()
                .get(get_By_Id, petId)
                .then()
                .body("id", is(petId))
                .statusCode(SC_OK);
    }

    public ValidatableResponse getByStatus (String petStatus) {
        return given()
                .when()
                .param("status", petStatus)
                .log().all()
                .get(get_Pet_By_Status, petStatus)
                .then()
                .body("status", everyItem(equalTo(petStatus)))
                .statusCode(SC_OK);
    }

    public ValidatableResponse updExistingPet (Pet pet) {
        String updName = "Sharikas";            //пришлось хардкожить здесь и в тесте для добавления проверок здесь и передачи с боди в тесте
        String updStatus = "pending";
        return given()
                .body(pet)
                .when()
                .post(update_Existing_Pet)
                .then()
                .body("name", is(updName))
                .body("status", is(updStatus))
                .statusCode(SC_OK);
    }

    public ValidatableResponse updWithFormData (long petID) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", ">|<0p1k")
                .formParam("status", "sold")
                .when()
                .post(update_Pet_With_Form_Data, petID)
                .then()
                .body("message", anyOf(is(petID), is(String.valueOf(petID))))
                .statusCode(SC_OK);
    }
}
