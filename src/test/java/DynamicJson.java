import Files.Payload;
import Files.Reusable_Methods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
//1) Dynamically build Json payload with external data inputs

public class DynamicJson {
    @Test(dataProvider = "BooksData")
    public void addBook(String isbn,String aisle){
        RestAssured.baseURI="http://216.10.245.166";
       String response= given().log().all().header("Content-Type","application/json")
                .body(Payload.AddBook(isbn,aisle)).
        when()
                .post("Library/Addbook.php").
                then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
       JsonPath js= Reusable_Methods.rawToJson(response);
       String id=js.get("ID");
        System.out.println(id);
    }
    //Delete Book

    //Actually annotation called DataProvider helps us to do parameterization for all the testcases and this DataProvider
    // Annotation is brought us by TestNG.
    //Array-Collection of elements
    //Multi-Dimensional Array= Collection of Arrays
    //Each Array has information about one book
    //When I link this DataProvider with above test then above test will blindly run the no of times as we will pass
    //no of Arrays in DataProvider

    //Parameterize the API Tests with multiple data sets-------------------
    @DataProvider(name="BooksData")
    public Object[][] getData(){
       return new Object[][]{
                {"dgjsd","2564"}, {"fgshd","7896"},{"dfghw","4853"}
        };
    }
}
