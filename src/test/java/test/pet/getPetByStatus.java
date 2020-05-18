package test.pet;

import endPoint.PetEndpoint;
import io.restassured.response.ValidatableResponse;
import model.Pet;
import model.Status;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
public class getPetByStatus {

    @Steps
    private PetEndpoint petEndpoint;
    private long createdPetId;
    private final Status status;

    public getPetByStatus(Status status) {
        this.status = status;
    }

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {Status.AVAILABLE},
                {Status.SOLD},
                {Status.PENDING}
        });
    }

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

    @Test
    public void getByStatus() {
        petEndpoint.getByStatus(status);
    }

    @After
    public void deleteCreatedPreconditions() {
        petEndpoint.deletePet(createdPetId);
    }
}




