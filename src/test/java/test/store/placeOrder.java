package test.store;

import endPoint.PetEndpoint;
import endPoint.StoreEndpoint;
import io.restassured.response.ValidatableResponse;
import model.Order;
import model.OrderStatus;
import model.Pet;
import model.Status;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static model.Pet.builder;

@RunWith(SerenityRunner.class)
public class placeOrder {

    @Steps
    StoreEndpoint storeEndpoint;

    @Steps
    Random random = new Random();

    @Steps
    PetEndpoint petEndpoint;
    long createdPetId;
    int orderId;

    @Before
    public void createPreconditions() {
        Pet pet = builder()
                .id("0")
                .name("Alik")
                .status(Status.AVAILABLE)
                .build();
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @After
    public void deleteOrderById () {
        storeEndpoint.deleteOrderById(String.valueOf(orderId));
    }

    public void delPet(){
        petEndpoint.deletePet(createdPetId);
    }

    @Test
    public void placeOrder () {
        Order order = Order.builder()
                .id((random.nextInt((10 - 1) + 1)+1))
                .petId(String.valueOf(createdPetId))
                .status(OrderStatus.PLACED)
                .shipDate(System.currentTimeMillis())
                .complete(Boolean.TRUE)
                .build();
        ValidatableResponse responseOrder = storeEndpoint.placeOrder(order);
        orderId = responseOrder.extract().path("id");
    }
}
