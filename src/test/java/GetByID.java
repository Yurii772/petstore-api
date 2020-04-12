import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetByID {

    PetEndpoint petEndpoint=new PetEndpoint();
    long createdPetId;

    @Before
    public void createPreconditions() {
       // int id=0;
        String name="Alik";
        String body = "{\n" +
                "  \"id\":\"0\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        ValidatableResponse response=petEndpoint.createPet(body);
        createdPetId=response.extract().path("id");
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