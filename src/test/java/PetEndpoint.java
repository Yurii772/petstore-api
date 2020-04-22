import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class PetEndpoint {
    private final static String GET_BY_ID="/pet/{id}";
    private final static String GET_PET_BY_STATUS="/pet/findByStatus?status{status}";
    private final static String UPDATE_EXISTING_PET="/pet";
    private final static String UPDATE_PET_WITH_FORM_DATA="/pet/{id}";
    private final static String DELETE_PET="/pet/{id}";
    private final static String CREATE_PET="/pet";
    private final static String UPLOAD_IMG="/pet/{id}/uploadImage";

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
                .post(CREATE_PET)
                .then()
                .statusCode(SC_OK);
    }

    public ValidatableResponse deletePet (long petId) {
        return given()
                .header("api_key", "special-key")
                .when()
                .delete(DELETE_PET, petId)
                .then()
                .body("message", is(String.valueOf(petId)))
                .statusCode(SC_OK);
    }

    public ValidatableResponse getById(long petId) {
        return given()
                .when()
                .get(GET_BY_ID, petId)
                .then()
                .body("id", is(petId))
                .statusCode(SC_OK);
    }

    public ValidatableResponse getByStatus (String petStatus) {
        return given()
                .when()
                .param("status", petStatus)
                .log().all()
                .get(GET_PET_BY_STATUS, petStatus)
                .then()
                .body("status", everyItem(equalTo(petStatus)))
                .statusCode(SC_OK);
    }

    public ValidatableResponse updExistingPet (Pet pet) {
        String updName = "Sharikas";            //пришлось хардкожить здесь и в тесте для добавления проверок здесь и передачи с боди в тесте
        return given()
                .body(pet)
                .when()
                .post(UPDATE_EXISTING_PET)
                .then()
                .body("name", is(updName))
                .body("status", is(String.valueOf(Status.PENDING)))
                .statusCode(SC_OK);
    }

    public ValidatableResponse updWithFormData (long petID) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", ">|<0p1k")
                .formParam("status", "sold")
                .when()
                .post(UPDATE_PET_WITH_FORM_DATA, petID)
                .then()
                .body("message", anyOf(is(petID), is(String.valueOf(petID))))
                .statusCode(SC_OK);
    }

    public ValidatableResponse uploadPhoto (long petID) {
        return given()
                .contentType("multipart/form-data")
                .header("api-key", "special-key")
                .multiPart(new File("/Users/yuriikravchenko/Desktop/Screenshot 2020-04-12 at 16.45.52.png"))
                .when()
                .post(UPLOAD_IMG, petID)
                .then()
                .body("message", is("additionalMetadata: null\nFile uploaded to ./Screenshot 2020-04-12 at 16.45.52.png, 224353 bytes"))
                .statusCode(SC_OK);
    }
}
