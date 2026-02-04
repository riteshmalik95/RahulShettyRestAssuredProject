import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class BugTest {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://riteshmalikmiet.atlassian.net/";
        String createIssueResponse=given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic cml0ZXNobWFsaWttaWV0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA3UV9RaEFrU0J4ZU5xM0k2THdiWG1hY3AxMW9uQ2drcVBNRWRnR21kNTlGN1pOZHFqMXRxcVU1M1JEdzZpclpXeTRXcVpTVXoyMVZJYnlaVnF0ZmR4RkRfZi1lTVVwclZLcXVXNWFHakd5bzRrNTRRTGd4RzVIaUVxam5rby1NbmMybzcxdXVTYU5iaVJoNE5DZ1BtSzJYdFVrNHRWRDVFelgzeENGLW92c2c9MjRBMUI5NTQ=")
                .body(Payload.CreateBug()).log().all()
                .post("rest/api/3/issue")
                .then().log().all().assertThat().statusCode(201)
                .extract().response().asString();

        JsonPath js=new JsonPath(createIssueResponse);
        String issueId=js.getString("id");
        System.out.println(issueId);
    }
}