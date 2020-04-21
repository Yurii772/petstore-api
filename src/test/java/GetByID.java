import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetByID {

    PetEndpoint petEndpoint=new PetEndpoint();
    long createdPetId;

    @Before
    public void createPreconditions() {
        Pet pet = new Pet("0", "Alik", Status.AVAILABLE);
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