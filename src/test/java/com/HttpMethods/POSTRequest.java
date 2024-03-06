package com.HttpMethods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;

public class POSTRequest 
{
	public static void main(String[] args) 
	{
		 JSONObject request = new JSONObject();
	        request.put("name", "Mallamma");
	        request.put("job", "QA");
		
		RestAssured.baseURI="https://reqres.in/api/";
		
		String response=given().log().all()
			.header("Content-Type","application/json")
			.body(ContentType.JSON)
			.accept(ContentType.JSON).
			body(request.toString()).
		when()
			.post("users").
		then()
			.assertThat().statusCode(201).extract().response().asString();
		
		JsonPath  js=new JsonPath(response);
		String name =js.getString("name");
		System.out.println("Name is :"+name);
		
	}
}
