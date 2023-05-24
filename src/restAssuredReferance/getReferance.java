package restAssuredReferance;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

public class getReferance {

	public static void main(String[] args) {
		RestAssured.baseURI="https://reqres.in/";
		
		int statusCode=given().header("content-type","application/Json").when().get("api/users?page=2").then().extract().statusCode();
		String responsebody=given().header("content-type","application/Json").when().get("api/users?page=2").then().extract().response().asString();
		System.out.println("Status code is" +statusCode+ "ok");
		System.out.println(responsebody);
		
		//expected result
		int id[]= {7,8,9,10,11,12};
		String[] email= {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"};
		String[] first_name= {"Michael","Lindsay","Tobias","Byron","George","Rachel"};
	    String[] last_name={"Lawson","Ferguson","Funke","Fields","Edwards","Howell"};
	    String[] avatar= {"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg",
		           "https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg",
		           "https://reqres.in/img/faces/11-image.jpg","https://reqres.in/img/faces/12-image.jpg"};
	      JsonPath jspres=new JsonPath(responsebody);
	      int count=jspres.getList("data").size();
	      System.out.println(count);
	      //validate each object
	      for(int i=0;i<count;i++) {
	    	  //expected result
	    	  int exp_id=id[i];
	    	  String exp_first_name=first_name[i];
	    	  String exp_last_name=last_name[i];
	    	  String exp_email=email[i];
	    	  String exp_avatar=avatar[i];
	    	  
	    	  int res_id=jspres.getInt("data["+i+"].id");
	    	  String res_email=jspres.getString("data["+i+"].email");
	    	  String res_first_name=jspres.getString("data["+i+"].first_name");
	    	  String res_last_name=jspres.getString("data["+i+"].last_name");
	    	  String res_avatar=jspres.getString("data["+i+"].avatar");
	    	  
	    	  //validation
	    	  Assert.assertEquals(res_id,exp_id,"ID at index"+i);
	    	  Assert.assertEquals(res_email,exp_email,"Email at index"+i);
	    	  Assert.assertEquals(res_first_name,exp_first_name,"First name at index"+i);
	    	  Assert.assertEquals(res_last_name,exp_last_name,"last name at index"+i);
	    	  Assert.assertEquals(res_avatar,exp_avatar,"avatar at index"+i);
	    	  System.out.println(res_email);
	    	  
	    	  
	      }
	      
	}
}
