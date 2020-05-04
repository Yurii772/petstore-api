import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

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
        SerenityRest.filters(new RequestLoggingFilter(LogDetail.ALL));
        SerenityRest.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    private RequestSpecification given() {
        return SerenityRest
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }

    @Step
    public ValidatableResponse createPet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(CREATE_PET)
                .then()
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse deletePet (long petId) {
        return given()
                .header("api_key", "special-key")
                .when()
                .delete(DELETE_PET, petId)
                .then()
                .body("message", is(String.valueOf(petId)))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getById(long petId) {
        return given()
                .when()
                .get(GET_BY_ID, petId)
                .then()
                .body("id", is(petId))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getByStatus (Status status) {
        return given()
                .when()
                .param("status", status)
                .log().all()
                .get(GET_PET_BY_STATUS, status)
                .then()
                .body("status", everyItem(equalTo(status.toString())))
                .statusCode(SC_OK);
    }

    @Step
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

    @Step
    public ValidatableResponse updWithFormData (long petID) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", ">|<0p1k")
                .formParam("status", Status.AVAILABLE)
                .when()
                .post(UPDATE_PET_WITH_FORM_DATA, petID)
                .then()
                .body("message", is(String.valueOf(petID)))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse uploadPhoto (long petID, String fileName) {

        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());

        return given()
                .contentType("multipart/form-data")
                .header("api-key", "special-key")
                .multiPart(file)
                .when()
                .post(UPLOAD_IMG, petID)
                .then()
                .body("message", allOf(containsString("File uploaded"), containsString(file.getName())))
                .statusCode(SC_OK);
    }
}




