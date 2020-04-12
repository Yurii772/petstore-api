import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class updatePetWithFormDataCase {
    long createdPetId;
    PetEndpoint petEndpoint=new PetEndpoint();

    @Before
    public void createPreconditions() {
        String body = "{\n" +
                "  \"id\":\"0\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Alik\",\n" +
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
        ValidatableResponse response = petEndpoint.createPet(body);
        createdPetId=response.extract().path("id");
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
