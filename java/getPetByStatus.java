import io.restassured.response.ValidatableResponse;
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
public class getPetByStatus {

    @Steps
    private PetEndpoint petEndpoint;
    private long createdPetId;
    private final Status status;

    public getPetByStatus(Status status) {
        this.status = status;
    }

    @TestData
    public static List<Object[]> testData() {
        return Arrays.asList(new Enum[][]{
                {Status.AVAILABLE},
                {Status.SOLD},
                {Status.PENDING}
        });
    }

    @Before
    public void createPreconditions() {
        Pet pet = new Pet("0", "Alik", status);
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




