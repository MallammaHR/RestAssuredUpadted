package com.HttpMethods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import com.files.Payload;
import com.files.ReUsableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GMapPostRequest 
{
	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		Response apiResponse = given()
		        .log().all()
		        .queryParam("key", "qaclick123")
		        .header("Content-Type", "application/json")
		        .body(Payload.AddPlace())
		    .when()
		        .post("maps/api/place/add/json")
		    .then()
		        .log().all()
		        .statusCode(200)
		        .body("scope", equalTo("APP"))
		        .header("server", "Apache/2.4.52 (Ubuntu)")
		        .extract().response();
//1.using JSONPath class we can 
		String responseBody = apiResponse.asString();
		JsonPath jsonPath = ReUsableMethods.rawToJson(responseBody);
		String placeID = jsonPath.getString("place_id");
		System.out.println("Place id is : " + placeID);
		System.out.println("the response is :"+apiResponse);

		//Add place-->update place with address==>get a place to validate if address is present in response
		//update place
		
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeID+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));			
		
		//GET place
	
		String getPlaceRespons=given()
				.log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", placeID).
		when()
				.get("maps/api/place/get/json").
		then()
				.assertThat().log().all().statusCode(200).extract().response().asString();
				
				JsonPath js1=ReUsableMethods.rawToJson(getPlaceRespons); 
				String actualAddress=js1.getString("address");
				System.out.println("The actual address is :"+actualAddress);	
				Assert.assertEquals(actualAddress,newAddress, "Actual address is not Matching");
	}
}
