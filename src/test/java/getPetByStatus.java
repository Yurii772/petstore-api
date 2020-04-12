import org.junit.Test;

public class getPetByStatus {

    PetEndpoint petEndpoint = new PetEndpoint();
    String status="sold";

    @Test
    public void getByStatus() {
        petEndpoint.getByStatus(status);
    }
}


