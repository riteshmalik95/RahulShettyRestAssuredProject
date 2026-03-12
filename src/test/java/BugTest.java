import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.File;

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

        given()
                //You’re using a path parameter, not hardcoding the issue id.
                .pathParams("key",issueId)
                .header("X-Atlassian-Token","no-check")
                //Auth header is allowed with multipart.
                //You did NOT set Content-Type manually → 👏 perfect
                .header("Authorization", "Basic cml0ZXNobWFsaWttaWV0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA3UV9RaEFrU0J4ZU5xM0k2THdiWG1hY3AxMW9uQ2drcVBNRWRnR21kNTlGN1pOZHFqMXRxcVU1M1JEdzZpclpXeTRXcVpTVXoyMVZJYnlaVnF0ZmR4RkRfZi1lTVVwclZLcXVXNWFHakd5bzRrNTRRTGd4RzVIaUVxam5rby1NbmMybzcxdXVTYU5iaVJoNE5DZ1BtSzJYdFVrNHRWRDVFelgzeENGLW92c2c9MjRBMUI5NTQ=")
                .multiPart("file",new File("C:/Users/user/Downloads/LoginButton/login.png")).log().all()
                //“I use multiPart() and let Rest Assured handle content type and boundaries.”
                //{key}--> When post request interpret here then Rest Assured will assume this is a path parameters
                //and at runtime it will take the key value from path params and put it here in post request......
                /*You’re using a path parameter, not hardcoding the issue id.
                Interview line you can say:
                {key} gets replaced by issueId
                Matches Jira API contract exactly
                “I use pathParams so Rest Assured replaces {key} at runtime.”
                */
                .post("rest/api/3/issue/{key}/attachments")
                .then().log().all().assertThat().statusCode(200);
    }
}