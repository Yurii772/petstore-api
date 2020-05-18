package test.pet;

import endPoint.PetEndpoint;
import io.restassured.response.ValidatableResponse;
import model.Pet;
import model.Status;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetByID {

    @Steps
    private PetEndpoint petEndpoint;
    private long createdPetId;

    @Before
    public void createPreconditions() {
        Pet pet = Pet.builder()
                .id("0")
                .name("Alik")
                .status(Status.AVAILABLE)
                .build();
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }
    @After
    public void deleteCreatedPreconditions () {
        petEndpoint.deletePet(createdPetId);
    }
    @Test
   public void getPetById () {
        petEndpoint.getById(createdPetId);
    }
}