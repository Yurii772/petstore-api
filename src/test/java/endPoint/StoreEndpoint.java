package endPoint;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.Order;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;


public class StoreEndpoint {
    private final static String PLACE_ORDER = "/store/order";
    private final static String GET_ORDER_BY_ID = "/store/order/{orderId}";
    private final static String DELETE_ORDER_BY_ID = "/store/order/{orderId}";
    private final static String GET_INVENTORY = "store/inventory";


    private RequestSpecification given() {
        return SerenityRest
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }

    static {
        SerenityRest.filters(new RequestLoggingFilter(LogDetail.ALL));
        SerenityRest.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    @Step
    public ValidatableResponse placeOrder (Order order) {
        return given()
                .body(order)
                .post(PLACE_ORDER)
                .then()
                .body("id", is (order.getId()))
                .body("petId", is (Long.valueOf((order.getPetId()))))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse findOrderById (String orderId) {
        return given()
                .when()
                .log()
                .all()
                .get(GET_ORDER_BY_ID, orderId)
                .then()
                .body("id", is(Integer.valueOf(orderId)))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse deleteOrderById (String orderId) {
        return given()
                .log()
                .all()
                .when()
                .delete(DELETE_ORDER_BY_ID, orderId)
                .then()
                .body("message", is(String.valueOf(orderId)))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getInventory () {
        return given()
                .when()
                .get(GET_INVENTORY)
                .then()
                .statusCode(SC_OK);
    }
}
