import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class deletePet {
    long createdPetId;
    PetEndpoint petEndpoint = new PetEndpoint();

    @Before
    public void createPreconditions() {
        Pet pet = new Pet("0", "Alik", Status.SOLD);
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @Test
    public void delPet(){
        petEndpoint.deletePet(createdPetId);
    }
}

