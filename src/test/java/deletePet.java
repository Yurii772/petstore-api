import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

public class deletePet {
    long addedPetId;
    PetEndpoint petEndpoint = new PetEndpoint();
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
        ValidatableResponse response=petEndpoint.createPet(body);
        addedPetId = response.extract().path("id");
    }
    @Test
    public void delPet(){
        petEndpoint.deletePet(addedPetId);
    }
}

