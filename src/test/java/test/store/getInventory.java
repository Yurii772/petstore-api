package test.store;
import endPoint.StoreEndpoint;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class getInventory {

    @Steps
    StoreEndpoint storeEndpoint;

    @Test
    public void getInventory () {
        storeEndpoint.getInventory();

    }



}
