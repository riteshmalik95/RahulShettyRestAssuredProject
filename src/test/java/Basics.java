import Files.Payload;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {
    public static void main(String[] args) {
        //given()- all input details
        //when()-Submit the API---Resource and HTTP Methods will go under When() method
        //then()-Validate the Response
        RestAssured.baseURI = "https://rahulshettyacademy.com";
//        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
//                .body(Payload.AddPlace())
//                .when().post("maps/api/place/add/json")
//                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
//                .header("Server","Apache/2.4.52 (Ubuntu)");

        //Add Place-->Update place with New Address --> Get place to validate if new address is present in response

        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)")
                .extract().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);//for parsing json
        String placeid = js.getString("place_id");
        //So it can be reused in later API such as Updating and Deleting the same place........
        System.out.println(placeid);

        //Update place
        String newAddress = "70 Summer walk, Africa";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeid + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                //Resource Name-maps/api/place/update/json
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));


        //hdfedvcdfjodqwdhw456t4y6485gf--->"+placeid+"
        //Get Place
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", placeid)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js1 = new JsonPath(getPlaceResponse);//for parsing json
        String actualAddress=js1.getString("address");
        System.out.println(actualAddress);
        //Cucumber Junit/TestNG
    }
}
