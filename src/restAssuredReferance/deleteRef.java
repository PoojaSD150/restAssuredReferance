package restAssuredReferance;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

public class deleteRef{

    public static void main(String[] args) {

        // Step 1: Declare Base URL
        RestAssured.baseURI = "https://reqres.in/";

        
        // Step 3: Configure Response Body
        int statusCode=given().header("Content-Type","application/json").when().delete("api/users/2").then().extract().statusCode();
        	
        //validate status code
        Assert.assertEquals(statusCode,204);
        System.out.println("statusCode :" +204);
        
    	}
}