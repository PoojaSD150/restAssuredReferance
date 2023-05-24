package restAssuredReferance;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class postReferance {

    public static void main(String[] args) {
 
        // Step 1: Declare Base URL
        RestAssured.baseURI = "https://reqres.in/";
        

        // Step 2.1:save request body in local variable
        String requestBody = "{\r\n"
                + "    \"name\": \"morpheus\",\r\n"
                + "    \"job\": \"leader\"\r\n"
                + "}";
        //step2.1: extract request body parameter
        JsonPath jspRequest = new JsonPath(requestBody);
        String req_name=jspRequest.getString("name");
        String req_job=jspRequest.getString("job");
        
                  
        //step 3.1=configure request body
        int statusCode=given().header("Content-Type","application/json").body(requestBody).when().post("api/users").then().extract().statusCode();

        String responseBody = given().header("Content-Type","application/json").body(requestBody).when().post("api/users").then().extract().response().asString();

        // Step 3.2: Parse the response body
        JsonPath jsp = new JsonPath(responseBody);
        String res_name = jsp.getString("name");
        String res_job = jsp.getString("job");
        String res_id = jsp.getString("id");
        String res_createdAt = jsp.getString("createdAt");
       
        
        // Step 3.3: Validate the response body parameters
        Assert.assertEquals(statusCode, 201);
        Assert.assertEquals(res_name,req_name);
        Assert.assertEquals(res_job,req_job);

        // Validate "id" and "createdAt" using Assert
        Assert.assertNotNull(res_id);
        Assert.assertNotNull(res_createdAt);

        // Validate "createdAt" using slice method for date
        String expectedDate = new java.util.Date().toInstant().toString().substring(0, 10);
        String actualDate = res_createdAt.substring(0, 10);
        Assert.assertEquals(actualDate, expectedDate);
        

    	System.out.println("Status code is: "+ statusCode + " Created");
    
    	System.out.println(responseBody);
    	System.out.println("date" +expectedDate);
        
    }
}