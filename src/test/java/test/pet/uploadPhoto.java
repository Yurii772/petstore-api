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
import java.util.List;


@RunWith(SerenityParameterizedRunner.class)

public class uploadPhoto {

    @Steps
    private PetEndpoint petEndpoint;
    private long createdPetId;
    private final String fileName;

    public uploadPhoto(String fileName) {
        this.fileName = fileName;
    }

    @TestData
    public static List<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"cat2.jpeg"},
                {"non-img_file.pkg"},
                {"empty.txt"},
                {"ккккк.png"}
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

    @After
    public void deleteCreatedPreconditions () {
        petEndpoint.deletePet(createdPetId);
    }

    @Test
    public void uploadImage () {
        petEndpoint.uploadPhoto(createdPetId, fileName);
    }

}
