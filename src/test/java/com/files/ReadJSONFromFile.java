package com.files;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//C:\Users\Mallukinnis\OneDrive\Desktop\PostManTutorial\AddBookDetails.json
public class ReadJSONFromFile 
{
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) throws IOException
	{	//add book
		RestAssured.baseURI="http://216.10.245.166";
		System.out.println("************************************************************************");
		Response resp=
		given().log().all()
			.header("Content-Type","application/json")
			.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Mallukinnis\\OneDrive\\Desktop\\PostManTutorial\\AddBookDetails.json")))).
		when()
			.post("/Library/Addbook.php").
		then()
			.log().all().assertThat().statusCode(200).extract().response();
		
		//to extract String form JSON object we have JsonPath-to extract value string from  JSON Object
		String repsonseBody = resp.asString();
		JsonPath js=ReUsableMethods.rawToJson(repsonseBody);
		String ID=js.get("ID");
		System.out.println("Id value is : "+ID);	
		
		//delete book
		given()
		
			.log().all().header("Content-Type","application/json")
			.body(Payload.DeleteBook(ID)).
		when()
			.delete("/Library/Addbook.php").
		then()
			.log().all().assertThat().statusCode(200);
	
		System.out.println("ID is deleted : "+ID);
		System.out.println("************************************************************************");

		
		
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new  Object[][] {{"aaa","444"},{"bbb","555"},{"ccc","666"}};
		
	}
}
