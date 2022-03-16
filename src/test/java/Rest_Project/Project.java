package Rest_Project;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Project {
	
	@Test
	public void CreateUser(ITestContext var) {
		
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		String username="abc";
		String password ="abc";
		JSONObject obj = new JSONObject();
		obj.put("username", username);
		obj.put("firstName", "sai");
		obj.put("lastName", "baba");
		obj.put("email", "abc");
		obj.put("password", password);
		obj.put("phone", "43434");
		obj.put("userStatus", "0");
		
		Response res = RestAssured.given().contentType(ContentType.JSON)
		.body(obj.toJSONString())
		.when().post("/user").then().statusCode(200).log().all().extract().response();
		var.setAttribute("username", username);
		var.setAttribute("password", password);
		
		
	}
	
	@Test
	public void ModifyUser(ITestContext var) {
		
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
	String user = var.getAttribute("username").toString();
		JSONObject obj = new JSONObject();
		obj.put("username", user);
		obj.put("firstName", "sai");
		obj.put("lastName", "baba");
		obj.put("email", "abc");
		obj.put("password", "abc");
		obj.put("phone", "12345");
		obj.put("userStatus", "0");
		
		Response res = RestAssured.given().contentType(ContentType.JSON)
		.body(obj.toJSONString())
		.when().put("/user/user").then().statusCode(200).log().all().extract().response();
		
		}

	
	@Test
	public void LoginUser(ITestContext var) {
		
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
	   String user = var.getAttribute("username").toString();
	   String pass = var.getAttribute("password").toString();
	
		
		RestAssured.given()
		.queryParam("username",user).queryParam("password",pass)
		.when().get("/user/login").then().statusCode(200).log().all().extract().response();
		
		}

	@Test
	public void LogoutUser() {
		
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
	    RestAssured.given().when().get("/user/logout").then().statusCode(200).log().all().extract().response();
		
		}
	
	@Test
	public void DeleteUser(ITestContext var) {
		
		String user = var.getAttribute("username").toString();
		RestAssured.baseURI="https://petstore.swagger.io/v2";
	    RestAssured.given().when().get("/user/"+user).then().statusCode(200).log().all().extract().response();
		
		}
	
}
