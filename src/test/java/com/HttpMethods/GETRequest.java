package com.HttpMethods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
public class GETRequest 
{

	@Test(description="Verify status code for GET method-users/2 as 200")
	public static void verifyResponse() 
	{
		//given=all input details
		//when=action or an even that should happen whivh is specfied int given
		//then=validate the resonpse
		
		RestAssured.baseURI="https://reqres.in/api/";
		
		given()
			.header("Content-Type","application.json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON).
		when().
			get("user/2").
		then()
			.assertThat().statusCode(200).log().all();
		
			
		
	}

}
