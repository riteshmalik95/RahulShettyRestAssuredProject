import static io.restassured.RestAssured.*;

public class GetIssueTest {

    public static void main(String[] args) {

        baseURI = "https://riteshmalikmiet.atlassian.net";

        given()
                .header("Authorization",
                        "Basic cml0ZXNobWFsaWttaWV0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA3UV9RaEFrU0J4ZU5xM0k2THdiWG1hY3AxMW9uQ2drcVBNRWRnR21kNTlGN1pOZHFqMXRxcVU1M1JEdzZpclpXeTRXcVpTVXoyMVZJYnlaVnF0ZmR4RkRfZi1lTVVwclZLcXVXNWFHakd5bzRrNTRRTGd4RzVIaUVxam5rby1NbmMybzcxdXVTYU5iaVJoNE5DZ1BtSzJYdFVrNHRWRDVFelgzeENGLW92c2c9MjRBMUI5NTQ=")
                .header("Accept", "application/json")
                .log().all()
                .when()
                .get("/rest/api/3/issue/10109")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }
}
