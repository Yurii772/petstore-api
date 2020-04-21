import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class updatePetWithFormDataCase {
    long createdPetId;
    PetEndpoint petEndpoint=new PetEndpoint();
    @Before
    public void createPreconditions() {
        Pet pet = new Pet("0", "Alik", Status.SOLD);
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @After
    public void deleteCreatedPreconditions () {
        petEndpoint.deletePet(createdPetId);
    }

    @Test
    public void updateWithFormData() {
        petEndpoint.updWithFormData(createdPetId);
    }
}
