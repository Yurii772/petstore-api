import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

public class deletePet {
    long createdPetId;
    PetEndpoint petEndpoint = new PetEndpoint();

    @Before
    public void createPreconditions() {
        Pet pet = new Pet("0", "Alik", "sold");
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @Test
    public void delPet(){
        petEndpoint.deletePet(createdPetId);
    }
}

