import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class uploadPhoto {
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
    public void uploadImage () {
        petEndpoint.uploadPhoto(createdPetId, "Cat2.jpeg");
    }

    @Test
    public void uploadHugeImage () {
        petEndpoint.uploadPhoto(createdPetId, "world.topo.bathy.200407.3x21600x10800.png");
    }

    @Test
    public void uploadNonImageFormat () {
        petEndpoint.uploadPhoto(createdPetId, "non-img_file.pkg"); //пробел в имени файла не приемлем
    }

    @Test
    public void uploadNotExistingImg () {
        petEndpoint.uploadPhoto(createdPetId, "not_existing_file.jpeg");
    }

    @Test
    public void uploadImgRussianName () {
        petEndpoint.uploadPhoto(createdPetId, "ккккк.png");
    }
    @Test
    public void uploadImgZeroSize () {
        petEndpoint.uploadPhoto(createdPetId, )
    }

}
