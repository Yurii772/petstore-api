import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetPetCase {
    @Before
    public void before () {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        spec.setBaseUri("https://petstore.swagger.io/v2");
        spec.addHeader("Content-Type", "application/json");
        RestAssured.requestSpecification=spec.build();
    }

    @Test
    public void getPetByID() {
        int id = 6739257;
        given()
                .log()
                .all()
                .when()
                .get("/pet/{id}", id)
                .then()
                .log().all()
                .body("id", is(id))
                .statusCode(200);
    }
    @Test
    public void updatePetsCase() {
        int id = 6739257;
        String name = "Sharikas";
        String status = "sold";
        given()
                .contentType("application/x-www-form-urlencoded")
                .param( "name", name)
                .param("status", status)
                .log().all()
                .when()
                .post("/pet/{id}", id)
                .then()
                .log().all()
                .body( "message", anyOf(is(id), is(String.valueOf(id))))
                .statusCode(200);
    }

    @Test
    public void updateExPet(){

        String body = ("<Pet>\n" +
                "\t<id>6739257</id>\n" + //захардкоджено айді як вставити значення змінної?
                "\t<Category>\n" +
                "\t\t<id>0</id>\n" +
                "\t\t<name>string</name>\n" + // захаркоджено їм'я. аналогічно 50
                "\t</Category>\n" +
                "\t<name>Alik</name>\n" +
                "\t<photoUrls>\n" +
                "\t\t<photoUrl>string</photoUrl>\n" +
                "\t</photoUrls>\n" +
                "\t<tags>\n" +
                "\t\t<Tag>\n" +
                "\t\t\t<id>0</id>\n" +
                "\t\t\t<name>string</name>\n" +
                "\t\t</Tag>\n" +
                "\t</tags>\n" +
                "\t<status>available</status>\n" +
                "</Pet>");
        given()
                .contentType("application/xml")
                .body (body)
                .log().all()
                .when()
                .put("/pet/")
                .then()
                .log().all()
                .body("name", is("Alik"))
                .statusCode(200);
    }
    @Test
    public void addPetCase() {
        int id = 454;
        String name = "Harold";
        String body = "{\n" +
                "  \"id\":\""+id+"\",\n" +
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
        given()
                .contentType(ContentType.JSON)
                .body (body)
                .log().all()
                .when()
                .post("/pet/")
                .then()
                .log().all()
                .body("id", is(id))
                .body("name", is(name))
                .statusCode(200);
    }
    @Test
    public void delPet(){
        //preconditions
        int id = 600;
        String name = "Harold";
        String body = "{\n" +
                "  \"id\":\""+id+"\",\n" +
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
        given()
                .contentType(ContentType.JSON)
                .body (body)
                .log().all()
                .when()
                .post("/pet/");
        given()
                .log()
                .all()
                .header("api_key", "special-key")
                .when()
                .delete("/pet/{id}", id)
                .then()
                .log().all()
                .body("message", is(String.valueOf(id))) //скоріше за все, не валідна перевірка
                .statusCode(200);
    }
    @Test
    public void getByStatus (){
        String status="available";
        given()
                .when()
                .param("status", status)
                .log().all()
                .get ("/pet/findByStatus?status{status}", status)
                .then()
                .log().all()
                .body("status", everyItem(equalTo(status)))
                .statusCode(200);
    }
}




